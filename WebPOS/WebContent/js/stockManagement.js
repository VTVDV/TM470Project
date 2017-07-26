/**
 * JavaScript for use on the Stock Management page.
 */
function validate() 
{
	var valid = true;

	var sell = $("#createStock\\:recSell");
	var cash = $("#createStock\\:recCash");
	var exchange = $("#createStock\\:recEx");
	var sellValue = sell.val();
	var cashValue = cash.val();
	var exchangeValue = exchange.val();
	if (isNaN(cashValue)) 
	{
		cash.addClass("validation-error");
		valid = false;
	} 
	if (isNaN(sellValue)) 
	{
		sell.addClass("validation-error");
		valid = false;
	} 
	if (isNaN(exchangeValue)) 
	{
		exchange.addClass("validation-error");
		valid = false;
	} 
	if (sellValue == null) 
	{
		sell.value = 0.00;
	} 
	if (cashValue == null) 
	{
		cash.value = 0.00;
	} 
	if (exchangeValue == null) 
	{
		exchange.value = 0.00;
	}
	return valid;
}

function onStockClick(id)
{
	$("#modifyStockTab").css('display', 'block');
	$('.nav-tabs a[href="#modifyStock"]').tab('show');
	//$("#modStock\\:modName").val("nuggets");
	$("#modStock\\:modName").val($("#sr" + id).text());
	$("#modStock\\:modCatList").val($("#cid" + id).text())
	$("#modStock\\:modSell").val($("#sp" + id).text());
	$("#modStock\\:modCash").val($("#cash" + id).text());
	$("#modStock\\:modExchange").val($("#exch" + id).text());
	$("#modStock\\:modNotes").val($("#notes" + id).text());
	$("#modStock\\:modKeywords").val($("#keys" + id).text());
	$("#modStock\\:modSerial").prop('checked', $("#serial" + id).text());
	$("#modStock\\:modTest").prop('checked', $("#test" + id).text());
	$("#modStock\\:modId").text(id);
}

