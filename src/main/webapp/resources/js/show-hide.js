$(document).ready(function() {

    var $aboutLength = $('.author-about').text().length;
    if ($aboutLength < 500) {
        $('.author-show-more').css('display', 'none');
    }

	$('.author-show-more').on('click', function(e) {
		$('.author-about').toggleClass('author-about-expanded');
	});
});
