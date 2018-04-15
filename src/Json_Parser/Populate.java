/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json_Parser;

/**
 *
 * @author rushi
 */
public class Populate {
    
    public static void main(String args[])throws Exception
    {
                InsertYelpUserJSON a = new InsertYelpUserJSON();
		System.out.println("Inserting into USER NOW!!!");
		a.insert_user();
                System.out.println("Insertion complete");  
                InsertBusinessJSON b = new InsertBusinessJSON();
		System.out.println("Inserting into BUSINESS NOW!!!");
		b.insert_business();
                System.out.println("Business Insertion complete");
		InsertCheckinJSON c = new InsertCheckinJSON();
		System.out.println("Inserting into CHECKIN NOW!!!");
		c.insert_checkin();
                System.out.println("CheckIn Insertion complete");
                InsertReviewJSON d = new InsertReviewJSON();
		System.out.println("Inserting into REVIEW NOW!!!");
		d.insert_review();
                System.out.println("Review Insertion complete");

                
                
    }
}
