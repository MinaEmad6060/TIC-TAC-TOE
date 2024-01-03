/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author dell
 */
public class ContactDTO {
    String playerName;
    String Password;
    int score;
    boolean onLine;
    boolean available;
    String record;
    int player_History;

    public ContactDTO(String playerName, String Password, int score, boolean onLine, boolean available, String record, int player_History) {
        this.playerName = playerName;
        this.Password = Password;
        this.score = score;
        this.onLine = onLine;
        this.available = available;
        this.record = record;
        this.player_History = player_History;
    }
    

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public int getPlayer_History() {
        return player_History;
    }

    public void setPlayer_History(int player_History) {
        this.player_History = player_History;
    }
    
    
}
