package ObjectPool;
import java.util.ArrayList;
import java.util.List;

public class ObjectPoolManager {
    //volatile to insure that the state of instance is immediately reflected to all the threads
    public static volatile ObjectPoolManager instance =null;
    private List<DBConnection> free = null;
    private List<DBConnection> inUse = null;

    private static int INITIAL_POOL_SIZE = 3;
    private static int MAX_POOL_SIZE = 6;// max objects that can be created are 6


    private ObjectPoolManager(){
        free = new ArrayList<>();
        inUse = new ArrayList<>();
        for(int i  =0;i<INITIAL_POOL_SIZE;i++){
            free.add(new DBConnection());// initialized with initial pool size
        }
    }

    public static ObjectPoolManager getInstance(){
        if(instance ==null){// for creating only once 
            synchronized(ObjectPoolManager.class){
                if(instance ==null){// insuring only one thread is creating the instance 
                    instance = new ObjectPoolManager();
                }
            }
        }
        return instance;
    }

    public synchronized DBConnection getDbConnection(){
        DBConnection dbConnection = null;
        //if the fee list is empty check if the use list already has max resource in use
        // if yes then no more resources can be created 
        //else we can create new resource and add the same in the free list and get it and and it back in the 
        //inUse resource list

        if(free.isEmpty() && inUse.size() == MAX_POOL_SIZE){
            System.out.println("Max pool size reached");
            return dbConnection;//not allowed to create any more resources
        }
        else if(free.isEmpty() &&  inUse.size()<MAX_POOL_SIZE){
            free.add(new DBConnection());// as the no. of resources are still less than the max pool size, we can create more resources
        }

        dbConnection = free.remove(free.size()-1);// take out once resource for usage 
        inUse.add(dbConnection);

        return dbConnection;
    }
    public synchronized void releaseDBConnection(DBConnection dbConnection){
        if(dbConnection!=null){
            inUse.remove(dbConnection);
            free.add(dbConnection);

        }
    }

}
