package com.rongxin.cms.controller;

import java.util.List;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.cms.domain.BizArticleRule;
import com.rongxin.cms.service.IBizArticleRuleService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 规则内容关系Controller
 * 
 * @author rx
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/cms/rule")
public class BizArticleRuleController extends BaseController
{

}
