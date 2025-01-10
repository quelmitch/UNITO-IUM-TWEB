document.querySelectorAll('.faq-question').forEach(question => {
    question.addEventListener('click', function () {
        const answer = this.nextElementSibling;
        answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
    });
});