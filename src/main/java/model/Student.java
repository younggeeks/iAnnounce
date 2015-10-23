package model;

/**
 * Created by Gaffi on 10/23/15.
 */
public class Student {

    private String reg_no;
    private String first_name;
    private String last_name;
    private String phone;
    private String intake;
    private String program_code;

    public Student(String reg_no, String first_name, String last_name, String phone, String intake, String program_code) {
        this.reg_no = reg_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.intake = intake;
        this.program_code = program_code;
    }


    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getProgram_code() {
        return program_code;
    }

    public void setProgram_code(String program_code) {
        this.program_code = program_code;
    }
}
