/* Theme Name:iDea - Clean & Powerful Bootstrap Theme
 * Author:HtmlCoder
 * Author URI:http://www.htmlcoder.me
 * Author e-mail:htmlcoder.me@gmail.com
 * Version:1.0.0
 * Created:October 2014
 * License URI:http://wrapbootstrap.com
 * File Description: Initializations of plugins 
 */

//search
var a = $(".js-link");
    var c = $(".js-content");
    a.click(function(i) {
        i.preventDefault();
        if (c.hasClass("sr-only")) {
            c.removeClass("sr-only").addClass("open")
        } else {
            if (c.hasClass("open")) {
                c.removeClass("open").addClass("sr-only")
            }
        }
    });
	
    function g(i) {
        if (i.indexOf("&") != -1 || i.indexOf("<") != -1 || i.indexOf(">") != -1 || i.indexOf("'") != -1 || i.indexOf("\\") != -1 || i.indexOf("/") != -1 || i.indexOf('"') != -1 || i.indexOf("%") != -1 || i.indexOf("#") != -1) {
            return false
        }
        return true
    }
    $("#sub-pc").click(function() {
        var i = $.trim($("#searchword").val());
        if (i == "" || i == "Please enter the keyword" || !g(i)) {
            alert("Please enter the key words and submit it.");
            return false
        }
        $('input[name="keyword"]').val(encodeURI(i));
        $('form[name="searchform"]').submit()
    });
    $("#sub-mobile").click(function() {
        var i = $.trim($("#iptSword").val());
        if (i == "" || i == "Please enter the keyword" || !g(i)) {
            alert("Please enter the key words and submit it.");
            return false
        }
        $('input[name="keyword"]').val(encodeURI(i));
        $('form[name="searchform"]').submit()
    });

//dropmenu
(function($){
    $(document).ready(function(){

        //Show dropdown on hover only for desktop devices
        //-----------------------------------------------
        var delay=0, setTimeoutConst;
        if (Modernizr.mq('only all and (min-width: 768px)') && !Modernizr.touch) {
            $('.main-navigation .navbar-nav>li.dropdown, .main-navigation li.dropdown>ul>li.dropdown').hover(
            function(){
                var $this = $(this);
                setTimeoutConst = setTimeout(function(){
                    $this.addClass('open').slideDown();
                    $this.find('.dropdown-toggle').addClass('disabled');
                }, delay);

            },  function(){ 
                clearTimeout(setTimeoutConst );
                $(this).removeClass('open');
                $(this).find('.dropdown-toggle').removeClass('disabled');
            });
        };

        //Show dropdown on click only for mobile devices
        //-----------------------------------------------
        if (Modernizr.mq('only all and (max-width: 767px)') || Modernizr.touch) {
            $('.main-navigation [data-toggle=dropdown], .header-top [data-toggle=dropdown]').on('click', function(event) {
            // Avoid following the href location when clicking
            event.preventDefault(); 
            // Avoid having the menu to close when clicking
            event.stopPropagation(); 
            // close all the siblings
            $(this).parent().siblings().removeClass('open');
            // close all the submenus of siblings
            $(this).parent().siblings().find('[data-toggle=dropdown]').parent().removeClass('open');
            // opening the one you clicked on
            $(this).parent().toggleClass('open');
            });
        };



    }); // End document ready

})(this.jQuery);

//toolbar
function b(){
	h = $(window).height();
	t = $(document).scrollTop();
	if(t > h){
		$('#gotop').show();
	}else{
		$('#gotop').hide();
	}
}
$(document).ready(function(e) {
	b();
	$('#gotop').click(function(){
		$(document).scrollTop(0);	
	})
	$('#code').hover(function(){
			$(this).attr('id','code_hover');
			$('#code_img').show();
		},function(){
			$(this).attr('id','code');
			$('#code_img').hide();
	})
	
});

$(window).scroll(function(e){
	b();		
})

