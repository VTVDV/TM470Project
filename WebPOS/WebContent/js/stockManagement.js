/**
 * JavaScript for use on the Stock Management page.
 */

function showResults(data)
{
	if (data.status == "success")
	{
		alert("hi");
		document.getElementById('results').innerHTML = "<tr><th>Darude</th><th>Sandstorm</th><th>Du</th><th>Du</th><th>Du</th></tr>";
	}
}