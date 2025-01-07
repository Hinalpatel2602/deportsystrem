package src;

public class Log {
    private static Log instance;
    private StringBuilder logEntries;

    private Log() {
        logEntries = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        logEntries.append(entry).append("\n");
    }

    public String getEntries() {
        return logEntries.toString();
    }

	public static void writeLogToFile(String string) {
		// TODO Auto-generated method stub
		
	}
}

