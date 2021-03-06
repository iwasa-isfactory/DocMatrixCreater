import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * 
 * @author iwasa
 *
 */
public class FileOperator {
	
	/**
	 * Export string data to file
	 * @param filePath
	 * @param o_lines
	 */
	public void exporter(String filePath, String o_lines) {
		try{
			File file = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(o_lines);
			bw.close();
	    }catch(IOException e){
	    	System.out.println(e);
	    }
	}
	
	/**
	 * 
	 * @param filePath
	 * @param encoding
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private BufferedReader getReaderFromSystem(String filePath, String encoding) throws URISyntaxException, IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), encoding));
        return reader;
    }
	
	/**
	 * Import string data from file
	 * @param filePath
	 * @param encoding
	 * @param delimiter
	 * @param isHeader
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ArrayList<String[]> importer(String filePath, String encoding, String delimiter, boolean isHeader) throws IOException, URISyntaxException {
	        BufferedReader in = null;
	        ArrayList<String[]> lines = new ArrayList<String[]>();
	        try {
	            in = getReaderFromSystem(filePath, encoding);
	            String line = "";
	            while (in.ready()) {
	                line = in.readLine();
	                if (line.equals("") || line.length() == 0) {
	                    continue;
	                }
	                else if (isHeader) {
	                    isHeader = false;
	                    continue;
	                }
	                lines.add(line.split(delimiter));
	            }
	        } finally {
	            if (in != null) {
	                in.close();
	            }
	        }
	        return lines;
	 }
}
