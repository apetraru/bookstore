$(document).ready(function() {
    var showMoreText = '...More', showLessText = 'Less', collapsedHeight = $('<div>', {
        'class': 'author-about collapsed'
    }).css('height'), addExpander = function() {
        if (this.scrollHeight <= parseInt(collapsedHeight)) {
            $(this).find('.expanded').remove();
        } else if (!$(this).siblings('.expanded')[0]) {
            $('<div>', {
                'class': 'expanded'
            }).text(showMoreText).click(function() {
                    var btn = $(this);
                    var text = btn.siblings('.author-about')
                    var collapsed = text.hasClass('collapsed');
                    var height = collapsed ? text[0].scrollHeight : collapsedHeight;
                    text.animate({
                        'height': height
                    }, 400, function() {
                        text.toggleClass('collapsed');
                        text.css('height', '');
                        btn.text(collapsed ? showLessText : showMoreText);
                    });
                }).insertAfter(this);
        }
    };
    $('.author-about.collapsed').each(addExpander);
});

