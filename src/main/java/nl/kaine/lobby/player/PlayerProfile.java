package nl.kaine.lobby.player;

import nl.kaine.lobby.Lobby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerProfile {

    private Lobby lobby;

    private UUID uuid;
    private String rank;
    private int balance;

    /**
     * Creates base player profile data in SQL database
     * @param lobby
     * @param uuid
     * @throws SQLException
     */
    public PlayerProfile(Lobby lobby, UUID uuid) throws SQLException {
        this.lobby = lobby;
        this.uuid = uuid;

        PreparedStatement statement = lobby.getDatabase().getConnection().prepareStatement("SELECT rank, balance FROM player_info WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            rank = rs.getString(1);
            balance = rs.getInt(2);
        } else {
            rank = "Speler";
            balance = 5;
            PreparedStatement statement1 = lobby.getDatabase().getConnection().prepareStatement("INSERT INTO player_info (id, uuid, balance, rank) VALUES (" +
                    "default," +
                    "'" + uuid + "'," +
                    balance +
                    ",'" + rank + "'" +
                    ");");
            statement1.executeUpdate();
        }
    }

    public void setRank(String rank) {
        this.rank = rank;
        try {
            PreparedStatement statement = lobby.getDatabase().getConnection().prepareStatement("UPDATE player_info SET rank = ? WHERE uuid = ?");
            statement.setString(1, rank);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setBalance(int balance) {
        this.balance = balance;

        try {
            PreparedStatement statement = lobby.getDatabase().getConnection().prepareStatement("UPDATE player_info SET balance = ? WHERE uuid = ?");
            statement.setInt(1, balance);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRank() { return rank; }
    public int getBalance() { return balance; }

}
