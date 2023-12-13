document.querySelectorAll('input[name="light"]').forEach((elem) => {
	elem.onchange = () => {
		setTimeout(function () {
			document.querySelector('.first-block').style.display = "none";
			document.querySelector('.second-block').style.display = "block";
		}, 1000)
	}
})

document.querySelector('.second-block').onchange = () => {
	setTimeout(function () {
		document.querySelector('.second-block').style.display = "none";
		document.querySelector('.third-block').style.display = "block";
	}, 1000)
}

document.querySelector('.third-block').onchange = () => {
	setTimeout(function () {
		document.querySelector('.third-block').style.display = "none";
	}, 1000)
}
