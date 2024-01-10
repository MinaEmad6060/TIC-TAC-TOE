package DTO;

public class Player {
        String playerName;
	String password;
	int totalScore;
	boolean online;
	boolean available;
	String record;
	
	public Player(String playerName, String record){
		this.playerName=playerName;
		this.record=record;
	}
	
	Player(String playerName, String password, int totalScore, boolean online,boolean available){
		this.playerName=playerName;
		this.password=password;
		this.totalScore=totalScore;
		this.online=online;
		this.available=available;
	}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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
}
