$(document).ready(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $("#fullpage").fullpage({
        responsiveWidth: 0,
        responsiveSlides: true,
        navigation: true,
        navigationPosition: 'right',
        anchors: ['homeAnchor', 'aboutUSAnchor', 'aboutUmeedAnchor', 'galleryAnchor'],
        afterLoad: function( anchorLink, index){
            if(index===5){
                
            }
        }
    });
    $('select').material_select();
    $('.carousel').carousel({fullWidth: true, noWrap: false});
    $(window).on('hashchange',function(){
        let animationName = 'animated fadeInLeft';
        let animationEnd = 'animationend oAnimationEnd mozAnimationEnd webkitAnimationEnd webkitAnimationEnd';
        $('.contentBox').addClass(animationName).one(animationEnd, function(){
            $('.contentBox').removeClass(animationName);
        });

    });

});
function next() {
    $('.carousel').carousel('next', 1);
}