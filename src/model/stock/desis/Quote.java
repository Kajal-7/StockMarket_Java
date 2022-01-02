package model.stock.desis;
import java.io.Serializable;
import java.util.ArrayList;


public class Quote implements Serializable {

    static  ArrayList<Quote> list=new ArrayList<Quote>();
    private static ArrayList<Quote> newQuoteObjects=new ArrayList<Quote>();
	private static final long serialVersionUID = 1L;
	
	private String stockName;
	private double bestBuyPrice;
	private double bestSellPrice;
	
	 
	//Constructor  	  
	public Quote(String stockName,double bestBuyPrice,double bestSellPrice) {
		this.stockName=stockName;
		this.bestBuyPrice=bestBuyPrice;
		this.bestSellPrice=bestSellPrice;
		
		if(isPresent(this))
		{
			System.out.println(stockName+" stock is already present!");
		}
		else
		{
			list.add(this);
			newQuoteObjects.add(this);
		}
	}
	
	//getters
	public String getStockname(Quote obj) {
		return obj.stockName;
	}
	public double getBestBuyPrice(Quote obj) {
		return obj.bestBuyPrice;
	}
	public double getBestSellPrice(Quote obj) {
		return obj.bestSellPrice;
	}
	
	//setters
	public void setBestBuyPrice(Quote obj, double val) {
		obj.bestBuyPrice=val;
	}
	public void setBestSellPrice(Quote obj, double val) {
		obj.bestSellPrice=val;
	}
	public void setStockName(Quote obj, String val) {
		obj.stockName=val;
	}
	
	//Methods
	public void introduce(Quote obj) {
		System.out.println("Stock Name: "+obj.stockName+ "\nBest Buy Price: "+obj.bestBuyPrice+ "\nBest Sell Price: "+obj.bestSellPrice+ "\n");
	}
    boolean isPresent(Quote obj) {
		
		for(Quote temp:list) {
			if(temp.getStockname(temp).equals(obj.getStockname(obj)))
				return true;
		}
		return false;
	}
    //Static methods to serialise
	public static void serialise() {
		Serialisation.serialiseObject(newQuoteObjects);
	}
	public static void serialiseWholeList() {
		Serialisation.serialiseObject(list);
	}
	//static method to print all available Quote Objects
	public static void printStocks() {
		for(Quote temp:list)
			temp.introduce(temp);
	}
	//to find whether certain stock is present or not
    public static Quote findStock(String stockName) {
    	
    	for(Quote obj:list) {
    		if(obj.getStockname(obj).equals(stockName))
    			return obj;
    	}
    	return null;
    }
    //update Quote Object after placing order
	public static void updatePrice(String stockName, String direction,double val) {
    	
    	
		try
		{   Quote temp=findStock(stockName);
			if(temp.getStockname(temp).equals(stockName)) {
				if(direction.equals("Buy")) {
		    		if(temp.getBestBuyPrice(temp)==0 || temp.getBestBuyPrice(temp)<val)
		    		{
		    			temp.setBestBuyPrice(temp, val);
		    			
		    		}
		    			
		    	}
		    	else if(direction.equals("Sell")){
		    		if(temp.getBestSellPrice(temp)==0 || temp.getBestBuyPrice(temp)>val)
		    			{temp.setBestSellPrice(temp, val);
		    		
		    		}
		    	}
			}
		}catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
	
	

}
