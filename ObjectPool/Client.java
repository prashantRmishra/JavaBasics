package ObjectPool;
public class Client {
    public static void main(String[] args) {
        ObjectPoolManager manager = ObjectPoolManager.getInstance();
        //below will be served from the initial free resource list
        DBConnection dbc1 = null;
        try {
            dbc1 = manager.getDbConnection();
            // DBConnection dbc2 = manager.getDbConnection();
            // DBConnection dbc3 = manager.getDbConnection();

            // DBConnection dbc4 = manager.getDbConnection();
            // DBConnection dbc5 = manager.getDbConnection();
            // DBConnection dbc6 = manager.getDbConnection();

            // DBConnection dbc7 = manager.getDbConnection();

            // System.out.println(dbc7);// this should be null as only 6 can be created at max
        } finally {
           manager.releaseDBConnection(dbc1);
        }
    }
}
