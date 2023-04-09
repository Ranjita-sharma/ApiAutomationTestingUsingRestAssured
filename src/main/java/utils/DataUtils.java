/*
package utils;

public class DataUtils {
    public static String getDataFromTestDataFile(String Sheetname,String id, String field){
        String result = null;
        try{
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            Recordset recordset = connection.executeQuery(query);
            while (recordset.next()) {
                System.out.println(recordset.getField("ID") + " " + recordset.getField("First Name") + " "+ recordset.getField("Last Name") +  " " + recordset.getField("Age"));
            }
            recordset.close();
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    }
}
*/
