import java.sql.*;
import java.util.ArrayList;

public class SQL_Stmt extends PnP_GUI {

    private ArrayList<String> PnPCharacters = new ArrayList<>();

    public static void main(String[] args) throws SQLException {

    }

    public void fillListWithJTableObjects() {
        JTableList.add(super.getNameTable());
        JTableList.add(super.getGenderTable());
        JTableList.add(super.getAgeTable());
        JTableList.add(super.getOccupationTable());
        JTableList.add(super.getHeightTable());
        JTableList.add(super.getWeightTable());
        JTableList.add(super.getOriginTable());
        JTableList.add(super.getSpeciesTable());
        JTableList.add(super.getCourageTable());
        JTableList.add(super.getIntelligenceTable());
        JTableList.add(super.getCharismaTable());
        JTableList.add(super.getIntuitionTable());
        JTableList.add(super.getWillpowerTable());
        JTableList.add(super.getDexterityTable());
        JTableList.add(super.getPhysicalStrengthTable());
        JTableList.add(super.getAgilityTable());
        JTableList.add(super.getConstitutionTable());
        JTableList.add(super.getSpeedTable());
        JTableList.add(super.getPhysicalResilienceTable());
        JTableList.add(super.getEnduranceTable());
        JTableList.add(super.getMentallyResilienceTable());
        JTableList.add(super.getInitiativeTable());
        JTableList.add(super.getReachTable());
        JTableList.add(super.getLoadCapacityTable());
        JTableList.add(super.getAttackTable());
        JTableList.add(super.getParryTable());
        JTableList.add(super.getEvadeTable());
        JTableList.add(super.getRangedCombatTable());
        JTableList.add(super.getMentallyPotentialTable());
        JTableList.add(super.getPhysicallyPotentialTable());
        JTableList.add(super.getCombinedPotentialTable());
        JTableList.add(super.getMoneyTable());
        JTableList.add(super.getPsiTable());
        JTableList.add(super.getAdventureRankTable());
        JTableList.add(super.getCurrentAdventureEXPTable());
        JTableList.add(super.getAdventureLvlUpTable());
    }

    public int getLastCharID() {
        int LastCharID = 0;
        try (Connection DBConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pnp", "root", "tD87Byh13g")) {
            Statement stmt = DBConn.createStatement();
            ResultSet rset = stmt.executeQuery("select CharID from Figure where CharID = (select max(CharID) from FIGURE)");
            rset.first();
            LastCharID = Integer.valueOf(rset.getString(1));
        }
        catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return LastCharID;
    }

    public void testFunction() {
        fillListWithJTableObjects();
        for (int i = 0; i < 8; i++) {

        }
    }

    public void createNewCharacter() {
        if (super.getCharBox().getSelectedIndex() == 0) {
            try (Connection DBConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pnp", "root", "tD87Byh13g")) {

                int newCharID = getLastCharID() + 1;
                fillListWithJTableObjects();
                ArrayList<JTable> filledJTable = new ArrayList<>();
                ArrayList<JTable> notFilledJTable = new ArrayList<>();

                for (int i = 0; i < 1; i++) {
                    if (JTableList.get(i).getValueAt(0,1) != " ") {
                        filledJTable.add(JTableList.get(i));
                        System.out.println(JTableList.get(i).getValueAt(1,1));
                    }
                    else {
                        System.out.println("Es wurde kein Eintrag in: " + JTableList.get(i) + " gefunden.");
                        notFilledJTable.add(JTableList.get(i));
                    }
                }

                while (notFilledJTable.size() != 0) {
                    for (int i = 0; i < notFilledJTable.size(); i++) {
                        if (notFilledJTable.get(i).getValueAt(0,1) != " ") {
                            filledJTable.add(notFilledJTable.get(i));
                            notFilledJTable.remove(i);
                        }
                    }
                }
                /*
                String sql = "update Figure set CharID = ?, CharName = ?, Gender = ?, Age = ?, Occupation = ?, Height = ?" +
                        "Weight = ?, Origin = ?, Species = ?";
                 */
                String sql = "insert into Figure (CharID, CharName) values (?, ?)";
                PreparedStatement preStmt = DBConn.prepareStatement(sql);
                preStmt.setString(1, String.valueOf(newCharID));
                preStmt.setString(2, String.valueOf(filledJTable.get(0).getValueAt(0, 1)));
                /*
                preStmt.setString(3, (String) filledJTable.get(1).getValueAt(0, 1));
                preStmt.setString(4, (String) filledJTable.get(2).getValueAt(0, 1));
                preStmt.setString(5, (String) filledJTable.get(3).getValueAt(0, 1));
                preStmt.setString(6, (String) filledJTable.get(4).getValueAt(0, 1));
                preStmt.setString(7, (String) filledJTable.get(5).getValueAt(0, 1));
                preStmt.setString(8, (String) filledJTable.get(6).getValueAt(0, 1));
                preStmt.setString(9, (String) filledJTable.get(7).getValueAt(0, 1));
                */
                preStmt.executeQuery();
            }
            catch (SQLException e) {
                System.out.println("Error: " + e);
            }

            //super.getNameTable().getValueAt(0,1);
        }
    }

    public void fillCharComboBox() {
        try (Connection DBConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pnp", "root", "tD87Byh13g")) {
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
