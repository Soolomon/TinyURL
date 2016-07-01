package db;

import java.sql.*;



public class UrlManip {

    Connection connection=null;
    //set original data
    private PropertiesDB getparam=new PropertiesDB();
    private final String dburl = getparam.Get("links");
    private  final String user=getparam.Get("petro");
    private  final String pass=getparam.Get("mypassword");

    public void addLink(String longurl, String tinyurl) {
        String sql="INSERT INTO store (longurl, tinyurl) VALUES (?,?)";
        PreparedStatement stmt=null;
        try {
            connection=DriverManager.getConnection(dburl, user, pass);
            stmt=connection.prepareStatement(sql);
            stmt.setString(1,longurl);
            stmt.setString(2,tinyurl);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new CustomException();
            }
        }
    }
    public  String getLongurl(String tinyurl)  {
        String longlink=null;
        PreparedStatement stmt=null;
        String sql="SELECT  longurl from store where tinyurl = '"+tinyurl+"'";
        try {
            connection=DriverManager.getConnection(dburl, user, pass);
            stmt=connection.prepareStatement(sql);
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next())
                longlink=rs.getString("longurl");
        } catch (SQLException e) {
            throw new CustomException();
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException e) {
                throw new CustomException();
            }
        }
        return longlink;
    }


    public  String getTinyurl(String longurl) {
        String tinyurl=null;
        PreparedStatement stmt=null;
        String sql="SELECT  tinyurl from store where longurl = '"+longurl+"'";
        try {
            connection=DriverManager.getConnection(dburl, user, pass);
            stmt=connection.prepareStatement(sql);
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next())
                tinyurl=rs.getString("tinyurl");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new CustomException();
            }
        }
        return tinyurl;
    }
}

