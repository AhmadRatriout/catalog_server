import org.apache.log4j.BasicConfigurator;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import static spark.Spark.*;

public class catalogServer {

    public static void main(String[] args) {
        BasicConfigurator.configure();


        JSONObject book1 = new JSONObject();
        
        book1.put("topic", "distributed systems");
        book1.put("title", " RPCs for Noobs");
        book1.put("id", "1000");
        book1.put("price", "10");
        book1.put("quantity", "1");




        JSONObject book2 = new JSONObject();
        
        book2.put("topic", "distributed systems");
        book2.put("title", " How to get a good grade in DOS in 40 minutes a day");
        book2.put("id", "2000");
        book2.put("price", "20");
        book2.put("quantity", "2");


        JSONObject book3 = new JSONObject();
        
        book3.put("topic", "undergraduate school");
        book3.put("title", " Xen and the Art of Surviving Undergraduate School");
        book3.put("id", "3000");
        book3.put("price", "30");
        book3.put("quantity", "3");


        JSONObject book4 = new JSONObject();
        
        book4.put("topic", "undergraduate school");
        book4.put("title", "Cooking for the Impatient Undergrad");
        book4.put("id", "4000");
        book4.put("price", "40");
        book4.put("quantity", "4");
        
       
        JSONArray booksList = new JSONArray();
        
        booksList.put(book1);
        booksList.put(book2);
        booksList.put(book3);
        booksList.put(book4);

        try (FileWriter file = new FileWriter("books.json")) {

            file.write(booksList.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        get("/search/:value", (request, response) -> {

            String value = request.params(":value");
            JSONArray temp =new JSONArray();
            for (int i = 0; i < booksList.length(); i++)
                if (booksList.getJSONObject(i).get("topic").equals(value)) {
                    JSONObject temp1= new JSONObject();
                	temp1.put("title", booksList.getJSONObject(i).get("title"));
                	temp1.put("id", booksList.getJSONObject(i).get("id"));
                	System.out.println(temp1.toString());
                    temp.put(temp1);
                }
            return temp;

        });
        
        
        get("/info/:value", (request, response) -> {

            String value = request.params(":value");
            JSONArray temp =new JSONArray();
            for (int i = 0; i < booksList.length(); i++)
                if (booksList.getJSONObject(i).get("id").equals(value)) {
                    JSONObject temp1= new JSONObject();
                	temp1.put("title", booksList.getJSONObject(i).get("title"));
                	temp1.put("quantity", booksList.getJSONObject(i).get("quantity"));
                	temp1.put("price", booksList.getJSONObject(i).get("price"));
                	System.out.println(temp1.toString());
                    temp.put(temp1);
                }
            return temp;

        });
        
        
        get("/purchase/:value", (request, response) -> {

            String value = request.params(":value");
            for (int i = 0; i < booksList.length(); i++)
                if (booksList.getJSONObject(i).get("id").equals(value)) {
               
                	int quantity = Integer.parseInt((String) booksList.getJSONObject(i).get("quantity"));
                	
                	if(quantity > 0) {
                		
                		booksList.getJSONObject(i).remove("quantity");
                		booksList.getJSONObject(i).put("quantity", quantity-1+"");
                		return "<!DOCTYPE html>\n"
                				+ "<html>\n"
                				+ "<head>\n"
                				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                				+ "<style>\n"
                				+"body {\n"
                				+ "	background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);\n"
                				+ "	background-size: 400% 400%;\n"
                				+ "	animation: gradient 15s ease infinite;\n"
                				+ "	height: 100vh;\n"
                				+ "}\n"
                				+ "\n"
                				+ "@keyframes gradient {\n"
                				+ "	0% {\n"
                				+ "		background-position: 0% 50%;\n"
                				+ "	}\n"
                				+ "	50% {\n"
                				+ "		background-position: 100% 50%;\n"
                				+ "	}\n"
                				+ "	100% {\n"
                				+ "		background-position: 0% 50%;\n"
                				+ "	}\n"
                				+ "}"
                				+ ".label {\n"
                				+ "  color: white;\n"
                				+ "  padding: 8px;\n"
                				+ "  font-family: Arial;\n"
                				+ "}\n"
                				+ ".success { text-align:center; vertical-align:middle; float:left; margin-top:25%; margin-left:50%;} /* Green */\n"
            
                				+ "</style>\n"
                				+ "</head>\n"
                				+ "<body>\n"
                				+ "\n"
                				
                				+ "\n"
                				+ "<span class=\"label success\">Success</span>\n"
 
                				+ "\n"
                				+ "</body>\n"
                				+ "</html>";
                	} else if (quantity <= 0) {
                		return "<!DOCTYPE html>\n"
                				+ "<html>\n"
                				+ "<head>\n"
                				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                				+ "<style>\n"
                				+ "body {\n"
                				+ "	background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);\n"
                				+ "	background-size: 400% 400%;\n"
                				+ "	animation: gradient 15s ease infinite;\n"
                				+ "	height: 100vh;\n"
                				+ "}\n"
                				+ "\n"
                				+ "@keyframes gradient {\n"
                				+ "	0% {\n"
                				+ "		background-position: 0% 50%;\n"
                				+ "	}\n"
                				+ "	50% {\n"
                				+ "		background-position: 100% 50%;\n"
                				+ "	}\n"
                				+ "	100% {\n"
                				+ "		background-position: 0% 50%;\n"
                				+ "	}\n"
                				+ "}"
                				+ ".label {\n"
                				+ "  color: white;\n"
                				+ "  padding: 8px;\n"
                				+ "  font-family: Arial;\n"
                				+ "}\n"
                				+ ".danger { text-align:center; vertical-align:middle; float:left; margin-top:25%; margin-left:50%;} /* Red */ \n"
                				+ "</style>\n"
                				+ "</head>\n"
                				+ "<body>\n"
                				+ "\n"
                				
                				+ "\n"
                				+ "<span class=\"label danger\">Sorry, Book not available now</span>\n"
                				+ "\n"
                				+ "</body>\n"
                				+ "</html>";
                	}
                }
			return request;
    
        });


    }
}