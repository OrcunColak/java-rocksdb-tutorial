import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBTutorial {

    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) {
        // Set the database location on your file system
        String dbPath = "rocksdb-data";

        // Initialize RocksDB options
        try (final Options options = new Options().setCreateIfMissing(true)) {
            // Open a RocksDB instance
            try (RocksDB db = RocksDB.open(options, dbPath)) {

                // Putting data into RocksDB
                String key = "user1";
                String value = "John Doe";
                db.put(key.getBytes(), value.getBytes());
                System.out.println("Inserted: " + key + " => " + value);

                // Retrieve data
                byte[] retrievedValue = db.get(key.getBytes());
                System.out.println("Retrieved: " + key + " => " + new String(retrievedValue));

                // Update data
                String updatedValue = "Jane Doe";
                db.put(key.getBytes(), updatedValue.getBytes());
                System.out.println("Updated: " + key + " => " + updatedValue);

                // Delete data
                db.delete(key.getBytes());
                System.out.println("Deleted: " + key);

            } catch (RocksDBException e) {
                System.err.println("Error using RocksDB: " + e.getMessage());
            }
        }
    }
}
