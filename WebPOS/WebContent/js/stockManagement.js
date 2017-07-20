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

