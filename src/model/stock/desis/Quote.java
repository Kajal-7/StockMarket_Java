package model.stock.desis;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;


public class Quote implements Serializable {

    private static TreeMap<String, Quote> newQuoteObjmap = new TreeMap<>();
    private static TreeMap<String, Quote> Quotemap = new TreeMap<>(); 
    
	private static final long serialVersionUID = 1L;
	
	private String stockName;
	private double bestBuyPrice;
	private double bestSellPrice;
	
	 
	//Constructor  	  
	public Quote(String stockName,double bestBuyPrice,double bestSellPrice) {
		this.stockName=stockName;
		this.bestBuyPrice=bestBuyPrice;
		this.bestSellPrice=bestSellPrice;
		
		if(Quotemap.containsKey(stockName))
		{
			System.out.println(stockName+" stock is already present!");
		}
		else
		{
			newQuoteObjmap.put(stockName, this);
			Quotemap.put(stockName, this);
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

    //Static methods to serialise
	public static void serialise() {
		Serialisation.serialiseObject(newQuoteObjmap);
	}
	public static void serialiseWholeList() {
		Serialisation.serialiseObject(Quotemap);
	}
	//static method to print all available Quote Objects
	public static void printStocks() {
	
			 for (Map.Entry<String, Quote> e : Quotemap.entrySet())
			e.getValue().introduce(e.getValue());
	}
	//to find whether certain stock is present or not
    public static Quote findStock(String stockName) {
    	
    	 if(Quotemap.containsKey(stockName)) {
    		 return Quotemap.get(stockName);
    	 }
    	return null;
    }
    //update Quote Object after placing order
	public static void updatePriceAddOrder(String stockName, String direction,double val) {
    	
		Quote temp=findStock(stockName);
		if(temp==null) {
			throw new NullPointerException();
		}
		else if(direction.equals("Buy") && (temp.getBestBuyPrice(temp)==0 || temp.getBestBuyPrice(temp)<val))
			temp.setBestBuyPrice(temp, val);
		else if(direction.equals("Sell") && (temp.getBestSellPrice(temp)==0 || temp.getBestBuyPrice(temp)>val))
			temp.setBestSellPrice(temp, val);
    	
    }
	//method to update price on removal of object
    public static void updatePrice(String stockName, String direction,double val) {
    	
    	try {
		Quote temp=findStock(stockName);
		if(direction.equals("Buy"))
			temp.setBestBuyPrice(temp, val);
		else if(direction.equals("Sell") )
			temp.setBestSellPrice(temp, val);
    	}catch(NullPointerException e) {
    		e.getMessage();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
	
    //default method
	static void makeQuotemap(Quote obj) {
		Quote.Quotemap.put(obj.getStockname(obj),obj);
	}

}
