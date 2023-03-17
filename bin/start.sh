#!/bin/bash
################################################################################
#          FILE: start.sh
#         USAGE:
#   DESCRIPTION: This script used to run the rongxin-merchant-dashboard-api
#       OPTIONS: ---
#          BUGS: ---
#         NOTES: ---
#        AUTHOR: niuzhiqiang, niuzhiqiang90@foxmail.com
#  ORGANIZATION:
#       CREATED: Wed 28 Nov 2018 11:46:59 AM CST
#      REVISION: 1.0.0
################################################################################

set -o nounset
declare -r bin_name=$(dirname $0)
declare -r bin_dir=$(cd "$bin_name"; pwd)
declare -r bin_pdir=$(dirname $bin_name)
declare -r svc_bin="./libs/jlrx-backend-basic-api-1.0.0.jar"
declare -r svc_log="./logs/jlrx-backend-basic-api.log"
declare -r svc_pid_file="./proc.pid"


################################################################################
# get java home
################################################################################
function get_java_home()
{
    if [[ -d $HOME/opt/jdk/1.8.0 ]]; then
        java_home="${HOME}/opt/jdk/1.8.0"
    elif [[ -d $HOME/opt/jdk-1.8.0 ]]; then
        java_home="${HOME}/opt/jdk-1.8.0"
    elif [[ -d /opt/jdk-1.8.0 ]]; then
        java_home="/opt/jdk-1.8.0"
    elif [[ -d /usr/local/jdk/1.8.0 ]]; then
        java_home="/usr/local/jdk/1.8.0"
    fi
    echo ${java_home}
}

################################################################################
# run rongxin loan api gateway
# Arguments:
# Returns:
#
# Commits:
################################################################################
function run_svc_bin()
{
    local ip_address=$1
    local svc_port=$2

    local java_home=$(get_java_home)
    local JAVA_CMD="${java_home}/bin/java -Dspring.profiles.active=${NODE_ENV}"
    local db_name="rongxin_druid"

    if [[ $NODE_ENV == "dev" ]]; then
        NODE_HOST=${ip_address} NODE_PORT=${svc_port} CONSUL_HOST=localhost DATASOURCE_URL="jdbc:mysql://rongxin-redis-m-svc.dev:3306/${db_name}?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai" DATASOURCE_USER="sample_dbowner" DATASOURCE_PWD="TDkNWKSFatELG5pN" REDIS_HOST="rongxin-redis-m-svc.dev" REDIS_PORT=6279 ${JAVA_CMD} -jar ${svc_bin} >> ${svc_log} 2>&1 &
    fi

    if [[ $NODE_ENV == "test" ]]; then
        NODE_HOST=${ip_address} NODE_PORT=${svc_port} CONSUL_HOST=localhost DATASOURCE_URL="jdbc:mysql://10.30.94.174:3306/${db_name}?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai" DATASOURCE_USER="sample_dbowner" DATASOURCE_PWD="TDkNWKSFatELG5pN" REDIS_HOST=localhost REDIS_PORT=6279 ${JAVA_CMD} -jar ${svc_bin} >> ${svc_log} 2>&1 &
    fi

    if [[ $NODE_ENV == "stage" ]]; then
        NODE_HOST=${ip_address} NODE_PORT=${svc_port} CONSUL_HOST=localhost DATASOURCE_URL="jdbc:mysql://10.172.223.230:3306/${db_name}?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai" DATASOURCE_USER="sample_dbowner" DATASOURCE_PWD="TDkNWKSFatELG5pN" REDIS_HOST=localhost REDIS_PORT=6279 ${JAVA_CMD} -jar ${svc_bin} >> ${svc_log} 2>&1 &
    fi

    if [[ $NODE_ENV == "prod" ]]; then
        NODE_HOST=${ip_address} NODE_PORT=${svc_port} CONSUL_HOST=localhost DATASOURCE_URL="jdbc:mysql://rongxin-mysql-p-svc.prod:3306/${db_name}?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai" DATASOURCE_USER="dbowner" DATASOURCE_PWD="ZygE9iiWUrSN9m67" REDIS_HOST="rongxin-redis-m-svc.prod" REDIS_PORT=6279 ${JAVA_CMD} -jar ${svc_bin} >> ${svc_log} 2>&1 &
    fi

    local svc_pid=$(ps -ef | grep -w ${svc_bin} | grep ${svc_bin} | grep -v grep | awk '{print $2}')
    echo "{\"svc_bin\":\"$svc_bin\", \"pid\":\"${svc_pid}\"}" > ${svc_pid_file}
}


################################################################################
# the main function
################################################################################
function main()
{
    local ip_address=$1
    local svc_port=$2
    pushd ${bin_pdir} > /dev/null
    echo "#################### start ${svc_bin} #############################" >> ${svc_log}
    run_svc_bin $ip_address $svc_port
    popd > /dev/null
}

main $@
