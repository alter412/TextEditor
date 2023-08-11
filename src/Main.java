import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Main class of the application
// Implements ActionListener and override actionPerformed method
public class Main implements ActionListener {
    // main frame of the application
    JFrame frame;

    // panel which contains the textArea and the scrollPane
    JPanel panel;
    // scrollPane to make the textarea scrollable both vertically and horizontally as needed
    JScrollPane scroll;


    // menuBar of the application
    JMenuBar menuBar;

    // menus in the menu bar
    JMenu file,edit;

    //menu items of the file menu
    JMenuItem newFile,saveFile,openFile;

    // menu items of the edit menu
    JMenuItem cut,copy,paste,selectAll,close;


    // textarea to type and edit the text
    JTextArea textArea;

    Main(){
        // main frame with title Text Editor
        frame = new JFrame("Text Editor");
        // setting default exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setting bounds of the frame
        frame.setBounds(300,100,800,800);


        // menu bar of the application
        menuBar = new JMenuBar();
        // file menu initialization
        file = new JMenu("File");
        // edit menu initialization
        edit = new JMenu("Edit");
        // setting font properties
        file.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        // setting font properties
        edit.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        // menu items of the file menu
        openFile = new JMenuItem("Open");
        newFile = new JMenuItem("New");
        saveFile = new JMenuItem("Save As");

        // setting font of the menu items
        newFile.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        openFile.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        saveFile.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));;

        // adding action Listener to menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // menu items of the edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // setting font properties of menu items
        cut.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        copy.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        paste.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        selectAll.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        close.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));

        // adding action Listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // adding menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // setting menuBar to frame
        frame.setJMenuBar(menuBar);

        // initialization of textArea
        textArea = new JTextArea();
        // setting font properties
        textArea.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));

        // main panel
        panel = new JPanel(new BorderLayout(0,0));
        // setting border
        panel.setBorder(new EmptyBorder(5,5,5,5));
        // adding textarea to panel
        panel.add(textArea,BorderLayout.CENTER);
        // initialization of the scrollPane and setting its properties as displaying scroll bar when needed
        scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // adding scrollPane to panel
        panel.add(scroll);
        // adding panel to frame
        frame.add(panel);

        // making frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

    // overriding actionPerformed method and based on source of action event performing different actions
    @Override
    public void actionPerformed(ActionEvent e) {
        // calling textArea cut function when source is cut menu item
        if(e.getSource()==cut){
            textArea.cut();
        }
        // calling textArea copy function when source is copy menu item
        if(e.getSource()==copy){
            textArea.copy();
        }
        // calling textArea paste function when source is paste menu item
        if(e.getSource()==paste){
            textArea.paste();
        }
        // calling textArea selectAll function when source is SelectAll menu item
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        // calling exit function of the system to close
        if(e.getSource()==close){
            System.exit(0);
        }

        // making new Main class instance when newFile is source
        if(e.getSource()==newFile){
            Main newFrame = new Main();
        }

        // logic when openFile is the ActionEvent source
        if(e.getSource()==openFile){
            // File chooser to show file picker
            JFileChooser jfc = new JFileChooser();
            // showing open dialog
            int val = jfc.showOpenDialog(null);
            // when a file is selected
            if(val==JFileChooser.APPROVE_OPTION){
                // fetching the file
                File f = jfc.getSelectedFile();
                try {
                    // fileReader to read the file
                    FileReader fr = new FileReader(f);
                    // bufferedReader to read the file
                    BufferedReader br = new BufferedReader(fr);

                    // output string and intermediate string
                    String inter ="",output="";
                    // reading line by line while null is not returned
                    while((inter=br.readLine())!=null){
                        // adding the line to output string
                        output=output + inter+"\n";
                    }

                    // setting the textArea to output string
                    textArea.setText(output);
                    // closing the resources
                    br.close();
                    fr.close();

                    // handling exceptions
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }


        // when source is saveFile menu item
        if(e.getSource()==saveFile){
            // File chooser to show save file picker
            JFileChooser jfc = new JFileChooser();
            // save dialog file picker
            int val = jfc.showSaveDialog(null);
            // when a file is selected
            if(val==JFileChooser.APPROVE_OPTION) {
                // fetching file path
                String path = jfc.getSelectedFile().getAbsolutePath();
                // File object using the path fetched
                File f = new File(path);
                try {
                    // file writer to write the on the above file
                    FileWriter fw = new FileWriter(f);
                    // buffered writer to write on the above file
                    BufferedWriter bw = new BufferedWriter(fw);
                    // calling textArea write function
                    textArea.write(bw);
                    // showing conformation message dialog
                    JOptionPane.showMessageDialog(null,"File Saved Successfully");
                    // closing the resources
                    bw.close();
                    fw.close();

                    // handling exceptions
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }


        }

    }
}
