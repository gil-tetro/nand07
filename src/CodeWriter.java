import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CodeWriter {

    private FileWriter output;

    static int staticVar = 16;

    static int tempVar = 5;

    private int lines;

    private String name;


    public CodeWriter(File input) throws IOException {
        String fileName = input.getName(); //saves the path of the give file
        this.output = new FileWriter(fileName.replace(".vm", ".asm"));   //create and ouput file to write into using the path we received
        this.name = fileName.replace(".vm", "");

        write2output("// init"); //initialize the ram structure
        write2output("@256");
        write2output("D=A");
        write2output("@SP");
        write2output("M=D");
    }

    public void writeArithmetic(ArrayList<String> str) throws IOException {
        if (str.get(0).equalsIgnoreCase("add") || str.get(0).equalsIgnoreCase("sub") || str.get(0).equalsIgnoreCase("and")
                || str.get(0).equalsIgnoreCase("or")) {
            write2output("@SP");
            write2output("AM=M-1");
            write2output("D=M");
            write2output("A=A-1");

            if (str.get(0).equalsIgnoreCase("add")) {
                write2output("M=D+M");
            } else if (str.get(0).equalsIgnoreCase("sub")) {
                write2output("M=M-D");
            } else if (str.get(0).equalsIgnoreCase("and")) {
                write2output("M=D&M");
            } else if (str.get(0).equalsIgnoreCase("or")) {
                write2output("M=D|M");
            }

        } else if (str.get(0).equalsIgnoreCase("eq") || str.get(0).equalsIgnoreCase("lt")
                || str.get(0).equalsIgnoreCase("gt")) {
            write2output("@SP");
            write2output("AM=M-1");
            write2output("D=M");
            write2output("A=A-1");
            write2output("D=D-M");
            write2output("@" + (lines + 7));

            if (str.get(0).equalsIgnoreCase("eq")) {
                write2output("D;JEQ");
            } else if (str.get(0).equalsIgnoreCase("lt")) {
                write2output("D;JGT");
            } else if (str.get(0).equalsIgnoreCase("gt")) {
                write2output("D;JLT");
            }

            write2output("@SP");
            write2output("A=M-1");
            write2output("M=0");
            write2output("@" + (lines + 5));
            write2output("0;JMP");
            write2output("@SP");
            write2output("A=M-1");
            write2output("M=-1");

        } else if (str.get(0).equalsIgnoreCase("not")) {
            write2output("@SP");
            write2output("A=M-1");
            write2output("M=!M");

        } else if (str.get(0).equalsIgnoreCase("neg")) {
            write2output("@SP");
            write2output("A=M-1");
            write2output("M=-M");
        }
    }

    public void writePushPop(String command, String segment, String index) throws IOException {
        if (command.equals("C_PUSH")) {
            if (segment.equals("pointer")) {
                if (index == "0") {
                    write2output("@THIS");
                } else if (index == "1") {
                    write2output("@THAT");
                }
                write2output("D=M");
            } else if (segment.equals("static")) {
                write2output("@" + index);
                write2output("D=A");
                write2output("@" + this.name + "." + index);
                write2output("M=D");
                write2output("@SP");
                write2output("A=M");
                write2output("M=D");
                write2output("@SP");
                write2output("M=M+1");
            } else if (segment.equals("temp")) {
                write2output("@" + index);
                write2output("D=A");
                write2output("@" + (tempVar + Integer.parseInt(index)));
                write2output("M=D");
                write2output("@SP");
                write2output("A=M");
                write2output("M=D");
                write2output("@SP");
                write2output("M=M+1");
            } else if (segment.equals("constant")) {
                write2output("@" + index);
                write2output("D=A");
                write2output("@SP");
                write2output("A=M");
                write2output("M=D");
                write2output("@SP");
                write2output("M=M+1");
            } else if (segment.equals("local")) {
                write2output("@" + index);
                write2output("D=A");
                write2output("@LCL");
                write2output("D=D+M");
                //problem
                write2output("@SP");
                write2output("A=M");
                write2output("M=D");
                write2output("@SP");
                write2output("M=M+A");
            }
        }

    }

    private void write2output(String str) throws IOException {
        output.write(str + "\n");
        lines++;
    }

    public void close() throws IOException {
        this.output.close();
    }
}
