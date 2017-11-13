$(document).ready(function() {
    adjustWorkflow();
    $(window).on('resize', function() {
        adjustWorkflow();
    })
});

function adjustWorkflow() {
    var items = $('.ui-workflow-step').length;
    var elHeight = $('.ui-workflow-step').height() / 2; //Division by 2 because each pseudo which is skewed is only 50% of its parent.
    var skewOffset = Math.tan(45 * (Math.PI / 180)) * elHeight;
    var reduction = skewOffset + ((items - 1) * 4);
    var leftOffset = $('.ui-workflow-step').css('left').replace('px', '');
    var factor = leftOffset * (-1) - 2;
    $('.ui-workflow-step').css({
        'width': '-webkit-calc((100% + 4px - ' + reduction + 'px)/' + items + ')',
        'width': 'calc((100% + 4px - ' + reduction + 'px)/' + items + ')'
    });
    // 4px for borders on either side
    $('.ui-workflow-step:first-child').css({
        'width': '-webkit-calc((100% + 4px - ' + reduction + 'px)/' + items + ' + ' + factor + 'px)',
        'width': 'calc((100% + 4px - ' + reduction + 'px)/' + items + ' + ' + factor + 'px)'
    });
    $('.ui-workflow-step:last').css({
        'width': '-webkit-calc((100% + 4px - ' + reduction + 'px)/' + items + ' + ' + factor + 'px)',
        'width': 'calc((100% + 4px - ' + reduction + 'px)/' + items + ' + ' + factor + 'px)'
    });
    // 26px because to make up for the left offset. Size of last-child is also increased to avoid the skewed area on right being shown
    $('.ui-workflow-step span:first-child').css('padding-left', (skewOffset + 15) + "px");
    $('.ui-workflow-step:first-child span').css({
        'width': '-webkit-calc(100% - ' + factor + 'px)',
        'width': 'calc(100% - ' + factor + 'px)'
    });
    $('.ui-workflow-step:last span').css({
        'width': '-webkit-calc(100% - ' + factor + 'px)',
        'width': 'calc(100% - ' + factor + 'px)'
    });
}