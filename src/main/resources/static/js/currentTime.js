// Use the passed argument instead of directly accessing createdDateString
(function(createdDateString) {
    // Convert the string to a Date object
    var createdDate = new Date(createdDateString);

    // Get the current date and time
    var currentDate = new Date();

    // Calculate the time difference in milliseconds
    var timeDifference = currentDate - createdDate;

    // Calculate days, hours, minutes, and seconds
    var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

    // Format the time difference for display
    var formattedTimeDifference = '';

    if (days > 0) {
        formattedTimeDifference += days + '일 ';
    }

    if (hours > 0 && days === 0) {
        formattedTimeDifference += hours + '시간 ';
    }

    if (minutes > 0 && days === 0 && hours === 0) {
        formattedTimeDifference += minutes + '분 ';
    }

    if (seconds > 0 && days === 0 && hours === 0 && minutes === 0) {
        formattedTimeDifference += seconds + '초 ';
    }
    // Display the formatted time difference in the HTML
    var timeDifferenceElement = document.getElementById('timeDifference');
    timeDifferenceElement.textContent = formattedTimeDifference + '전';
})(createdDateString);
