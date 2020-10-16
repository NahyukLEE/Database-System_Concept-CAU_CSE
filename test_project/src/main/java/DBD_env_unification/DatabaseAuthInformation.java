package DBD_env_unification;

import java.io.*;

/*
* Written by Seonghun Lee on 6. Sep. 2020.
*
* This class parses the mysql database authentication file and stores the DB connection information.
* Setting the class member variables is only permitted in the parsing stage (parse_auth_info).
* */
public class DatabaseAuthInformation {
    private String host;
    private String port;
    private String database_name;
    private String username;
    private String password;

    /*
    * Class Constructor with no argument.
    * It initializes all member values as null.
    * */
    public DatabaseAuthInformation() {
        this.host = null;
        this.port = null;
        this.database_name = null;
        this.username = null;
        this.password = null;
    }

    /*
     * Parsing function based on given DB authentication file.
     * This function check the file verification first, and then parse the given file.
     *
     * Parameter
     *  - auth_filepath: String
     *      Path of the local DB authentication file.
     *
     * Return
     *  - Boolean
     *      Return true for successful data parsing, and false otherwise.
     *      Wrong file path or any missing on DB connection information is also regarded as fail.
     * */
    public boolean parse_auth_info(String auth_filepath) {
        String host = null;
        String port = null;
        String database_name = null;
        String username = null;
        String password = null;

        /* Parse */
        try {
            File file = new File(auth_filepath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.length() == 0) continue;
                if(line.charAt(0) == '#') continue;

                int line_length = line.length();
                if(line.substring(0, 4).equals("host")) host = line.substring(5, line_length);
                else if(line.substring(0, 4).equals("port")) port = line.substring(5, line_length);
                else if(line.substring(0, 8).equals("database")) database_name = line.substring(9, line_length);
                else if(line.substring(0, 8).equals("username")) username = line.substring(9, line_length);
                else if(line.substring(0, 8).equals("password")) password = line.substring(9, line_length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        /* Verification */
        boolean flag_verified = true;
        if(host == null) flag_verified = false;
        if(port == null) flag_verified = false;
        if(database_name == null) flag_verified = false;
        if(username == null) flag_verified = false;
        if(password == null) flag_verified = false;
        if(!flag_verified) {
            return false;
        }

        /* Apply parsed values */
        this.host = host;
        this.port = port;
        this.database_name = database_name;
        this.username = username;
        this.password = password;

        return true;
    }

    /*
     * Getter for host member variable.
     *
     * Return
     *  - String
     *      Return the host member variable.
     * */
    public String getHost() {
        return host;
    }

    /*
     * Getter for port member variable.
     *
     * Return
     *  - String
     *      Return the port member variable.
     * */
    public String getPort() {
        return port;
    }

    /*
     * Getter for database name member variable.
     *
     * Return
     *  - String
     *      Return the database name member variable.
     * */
    public String getDatabase_name() {
        return database_name;
    }

    /*
     * Getter for username member variable.
     *
     * Return
     *  - String
     *      Return the username member variable.
     * */
    public String getUsername() {
        return username;
    }

    /*
     * Getter for password member variable.
     *
     * Return
     *  - String
     *      Return the password member variable.
     * */
    public String getPassword() {
        return password;
    }

    /*
     * Print all member variable for debug purpose.
     *
     * Return
     *  - None
     * */
    public void debug_print() {
        System.out.println("Host: " + this.host + ":" + this.port + "/" + this.database_name + "@" + this.username + ":" + this.password);
    }
}
