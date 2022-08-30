package com.project1.models;

public class Reimbursement {

    private int id;
    private double amount;
    private String submitted;
    private String resolved;
    private String descritpion;
    private byte[] receipt;
    private int authorId;
    private int resolverId;
    private String status;
    private String type;

    public Reimbursement(int id, double amount, java.sql.Timestamp timestamp, java.sql.Timestamp timestamp2, String descritpion, byte[] receipt, int authorId, int resolverId, String status, String type){
        this.id = id;
        this.amount = amount;
        this.submitted = timestamp.toString();
        if(timestamp2 == null){
            this.resolved = "Not resolved";
        }
        else{
            this.resolved = timestamp2.toString();
        }
        this.descritpion = descritpion;
        this.receipt = receipt;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(double amount, String descritpion, int authorId){
        this.amount = amount;
        this.descritpion = descritpion;
        this.authorId = authorId;
    }

    public Reimbursement(){

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return this.submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return this.resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescritpion() {
        return this.descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public byte[] getReceipt() {
        return this.receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return this.resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", Amount='" + getAmount() + "'" +
            ", Submitted='" + getSubmitted() + "'" +
            ", Resolved='" + getResolved() + "'" +
            ", Description='" + getDescritpion() + "'" +
            ", Author ID='" + getAuthorId() + "'" +
            ", Resolved ID='" + getResolverId() + "'" +
            ", status='" + getStatus() + "'" +
            ", Type='" + getType() + "'" +
            "}";
    }

    
}
