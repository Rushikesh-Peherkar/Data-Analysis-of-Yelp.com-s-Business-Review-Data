/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json_Parser;
import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author rushi
 */
public class InsertReviewJSON {
   
    public void insert_review() throws SQLException
	{
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String sqlquery = "INSERT INTO REVIEWS" 
		+ "(funny_vote, useful_vote, cool_vote, user_id, review_id, stars, r_date, r_text, r_type, bid) VALUES" 
				+ "(?,?,?,?,?,?,?,?,?,?)";
		
		JSONParser parser = new JSONParser();
		try
		{
			dbConnection = getDBConnection();
                        if(dbConnection!=null)
                            System.out.println("Connected in review...");
			preparedStatement = dbConnection.prepareStatement(sqlquery);
			
			FileReader filereader = new FileReader("C:\\Users\\rushi\\Documents\\SCU 2nd Quarter\\Database Management Systems\\YelpDataset-CptS451\\yelp_review.json");
			BufferedReader bufferedReader = new BufferedReader(filereader);
			String line;
			while ((line = bufferedReader.readLine()) != null) 
			{
				Object obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) obj;
				
				JSONObject votes = (JSONObject) jsonObject.get("votes");
				int funny_votes;
				int useful_votes;
				int cool_votes;
				funny_votes = ((Long) votes.get("funny")).intValue();
				useful_votes = ((Long) votes.get("useful")).intValue();
				cool_votes = ((Long) votes.get("cool")).intValue();
				preparedStatement.setInt(1, funny_votes);
				preparedStatement.setInt(2, useful_votes);
				preparedStatement.setInt(3, cool_votes);
				
				String user_id = (String) jsonObject.get("user_id");
				preparedStatement.setString(4, user_id);
				
				String review_id = (String) jsonObject.get("review_id");
				preparedStatement.setString(5, review_id);
				
				int stars = ((Long) jsonObject.get("stars")).intValue();
				preparedStatement.setInt(6, stars);
				
				String date = (String) jsonObject.get("date");
				preparedStatement.setString(7, date);
				
				String text = (String) jsonObject.get("text");
				preparedStatement.setString(8, text);
				
				String type = (String) jsonObject.get("type");
				preparedStatement.setString(9, type);
				
				String business_id = (String) jsonObject.get("business_id");
				preparedStatement.setString(10, business_id);
				
				preparedStatement.executeUpdate();
				
			}
			filereader.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}
			if (dbConnection != null) 
			{
				dbConnection.close();
			}
		}

	}
	public static Connection getDBConnection() 
	{

		Connection dbConnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}		

}
