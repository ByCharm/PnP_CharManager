import java.sql.*;
import java.util.ArrayList;

public class SQL_Stmt extends PnP_GUI {

    private ArrayList<String> PnPCharacters = new ArrayList<>();

    public static void main(String[] args) throws SQLException {

    }

    public void fillCharComboBox() {
        try(Connection DBConn = DriverManager.getConnection(url, user, password)){
            //create sql stmt
            Statement stmt = DBConn.createStatement();
            //execute sql stmt
            ResultSet rset = stmt.executeQuery("select Name from FIGURE");

            while (rset.next()) {
                if (!PnPCharacters.contains(rset.getString(1))) {
                    PnPCharacters.add(rset.getString(1));
                }
            }

            for (String x : getArrayList()) {
                getCharBox().addItem(x);
            }
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    /*
    public void fillPnpFigure(int CharID){
        String ID = String.valueOf(CharID);
        try (Connection DBConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pnp", "root", "tD87Byh13g")){
            //create prepared sql stmt
            String sql = "select * from FIGURE where CharID = ?";
            PreparedStatement preStmt =  DBConn.prepareStatement(sql);
            preStmt.setString(1, ID);
            ResultSet rs = preStmt.executeQuery();
            rs.first();
            for(int i=2; i<=10; i++){
                switch (i){
                    case 2: super.getNameTable().setValueAt(rs.getString(i),0, 1);
                        break;
                    case 3: super.getGenderTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 4: super.getAgeTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 5: super.getOccupationTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 6: super.getHeightTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 7: super.getWeightTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 8: super.getOriginTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    case 9: super.getSpeciesTable().setValueAt(rs.getString(i), 0, 1);
                        break;
                    default: System.out.println("Error");
                        break;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    */
    public void chunkArray() {
        int[] test = {1,2,3,4,5,6,7,8,9,0};

        ArrayList<Integer> triple = new ArrayList<Integer>();
        int counter = 0;

        for (int i = 1; i < test.length; i++) {
            triple.add(test[i]);
            counter+=1;
            if (counter == 3) {
                for (int j = 0; j < triple.size(); j++) {
                    System.out.print(triple.get(j));
                }
                counter = 0;
                triple.clear();
                System.out.println("");
            }
        }
    }

    public ArrayList<String> getArrayList() {return PnPCharacters;}
}
