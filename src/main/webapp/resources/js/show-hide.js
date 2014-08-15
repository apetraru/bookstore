function aboutExpand() {
    var authorAbout = document.querySelector('.author-about');
    var authorShowMore = document.querySelector('.author-show-more');
    authorShowMore.addEventListener('click', function() {
        authorAbout.classList.toggle('author-about-expanded');
    });

}

window.onload = aboutExpand;