package dallsoft.com.pascratcherTwo;
import java.util.*;

/**
 * Created by jkwest on 11/20/2014.
 */
public class TicketItem implements Comparable
{

	@Override
	public int compareTo(Object p1)
	{
		// TODO: Implement this method
		return 0;
	}
	

    private String link;
    private String cost;
	private String gameNumber;
    private String prizeInfo = "";
    private String title;

    private String prizeA;
    private String prizeB;
    private String prizeC;
    private String prizeD;
    private String prizeE;
    private String prizeF;

    private String valueA;
    private String valueB;
    private String valueC;
    private String valueD;
    private String valueE;
    private String valueF;




    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGameNumber() {
        return gameNumber;
    }
	
	public void setCost(String cost){
		this.cost = cost;
		
	}
	public String getCost(){
		return cost;
	}

    public void setGameNumber(String strGameNumber) {
		
       this.gameNumber = (strGameNumber);
    }

    public String getPrizeInfo() {
        return prizeInfo.trim();
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = this.prizeInfo + prizeInfo + " ";
      //  this.prizeInfo = prizeInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
	
	
	/*Comparator for sorting the list by Game Name*/
    public static Comparator<TicketItem> GameNameComparator = new Comparator<TicketItem>() {

		public int compare(TicketItem s1, TicketItem s2) {
			String GameName1 = s1.getTitle().toUpperCase();
			String GameName2 = s2.getTitle().toUpperCase();

			//ascending order
			return GameName1.compareTo(GameName2);

			//descending order
			//return GameName2.compareTo(GameName1);
		}};
		
		
	/*Comparator for sorting the list by Game Number... newest first*/
    public static Comparator<TicketItem> GameNumberComparator = new Comparator<TicketItem>() {

		public int compare(TicketItem s1, TicketItem s2) {
			int GameNumber1 = Integer.parseInt(s1.getGameNumber());
			int GameNumber2 = Integer.parseInt(s2.getGameNumber());
		
				

			//ascending order
		//	return GameNumber1.compareTo(GameNumber2);

			//descending order
			
			
	    
			
		return GameNumber2-GameNumber1;
		
		}};
		
	public static Comparator<TicketItem> CostComparator = new Comparator<TicketItem>() {

		public int compare(TicketItem s1, TicketItem s2) {
			int gameCost1 = Integer.parseInt(s1.getCost());
			int gameCost2 = Integer.parseInt(s2.getCost());



			//ascending order
			//	return GameNumber1.compareTo(GameNumber2);

			//descending order




			return gameCost2-gameCost1;

		}};

    public String getPrizeA() {
        return prizeA;
    }

    public void setPrizeA(String prizeA) {
        this.prizeA = prizeA;
    }

    public String getPrizeB() {
        return prizeB;
    }

    public void setPrizeB(String prizeB) {
        this.prizeB = prizeB;
    }

    public String getPrizeC() {
        return prizeC;
    }

    public void setPrizeC(String prizeC) {
        this.prizeC = prizeC;
    }

    public String getPrizeD() {
        return prizeD;
    }

    public void setPrizeD(String prizeD) {
        this.prizeD = prizeD;
    }

    public String getPrizeE() {
        return prizeE;
    }

    public void setPrizeE(String prizeE) {
        this.prizeE = prizeE;
    }

    public String getPrizeF() {
        return prizeF;
    }

    public void setPrizeF(String prizeF) {
        this.prizeF = prizeF;
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

    public String getValueC() {
        return valueC;
    }

    public void setValueC(String valueC) {
        this.valueC = valueC;
    }

    public String getValueD() {
        return valueD;
    }

    public void setValueD(String valueD) {
        this.valueD = valueD;
    }

    public String getValueE() {
        return valueE;
    }

    public void setValueE(String valueE) {
        this.valueE = valueE;
    }

    public String getValueF() {
        return valueF;
    }

    public void setValueF(String valueF) {
        this.valueF = valueF;
    }
}
