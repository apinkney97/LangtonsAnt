/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonsant;

import java.io.File;

/**
 *
 * @author azp
 */
class CustomFileFilter extends javax.swing.filechooser.FileFilter {

    private String ex;
    private String desc;

    public CustomFileFilter(String extension, String description) {
        ex = extension;
        desc = description;
    }
    
    private CustomFileFilter() {
        //no thank you
    }

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with specified extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(ex);
    }

    @Override
    public String getDescription() {
        // This description will be displayed in the dialog,
        // hard-coded = ugly, should be done via I18N
        return desc + " (" + ex + ")";
    }
}
