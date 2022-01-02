package model.stock.desis;

import java.util.ArrayList;

public class IocOrder extends Order{

	
	public IocOrder(String stockname, double price, String tradingPartyName, String directionOfOrder,
			String typeOfOrder) {
		super(stockname, price, tradingPartyName, directionOfOrder, typeOfOrder);
	}


static Order isTradePossible(Order firstTradeParty) {
		
		Order secondTradeParty=null;
		ArrayList<Order> orderlist;
		
		if(firstTradeParty.getDirectionOfOrder().equals("Buy")) {
			orderlist=Order.sellorderlist;
		}
		else {
			orderlist=Order.buyorderlist;
		}
		
		for(Order obj: orderlist) {
			 if(obj.getStockName().equals(firstTradeParty.getStockName())&&
					 !(firstTradeParty.getTradingPartyName().equals(obj.getTradingPartyName()))) {
				 if(firstTradeParty.getDirectionOfOrder().equals("Buy") && firstTradeParty.getPrice()>obj.getPrice())
					 secondTradeParty=obj;
				 else if(firstTradeParty.getDirectionOfOrder().equals("Sell") && firstTradeParty.getPrice()<obj.getPrice())
					 secondTradeParty= obj;
			 }
		 }
		
		
		return secondTradeParty;
	}

	
	static void placeSpecificTypeOrder(Order obj) {
	
		  Order secondTradingParty=IocOrder.isTradePossible(obj);
		  
		  if(secondTradingParty!=null) {
			  Trade.createTrade(obj,secondTradingParty);
		  }
		 
	}
}
