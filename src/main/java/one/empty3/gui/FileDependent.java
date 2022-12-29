/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

/*
 *
 *  *  This file is part of Empty3.
 *  *
 *  *     Empty3 is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 2 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     Empty3 is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 *
 *
 */

package one.empty3.gui;

import one.empty3.library.Representable;
import one.empty3.library.Scene;
import one.empty3.library.StructureMatrix;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * Created by manue on 18-09-19.
 */
public class FileDependent  extends StructureMatrix<File> {
        private File currentDirectory;

        public FileDependent(int dim)
        {
            super(dim, File.class);
            currentDirectory = new File(".");
        }

        public File openFileDialog() {
            JFileChooser jFileChooser = new JFileChooser(currentDirectory);
            jFileChooser.showDialog(null, "Choose");
            if(jFileChooser.getSelectedFile()!=null) {
                File f = jFileChooser.getSelectedFile();
                if(f.exists() && !f.isDirectory())
                {
                    if(getDim()==0) {
                        setElem(f);
                        return f;
                    }
                    if(getDim()==1) {
                        add(1, f);
                        return f;
                    }
                }
            }
            currentDirectory = jFileChooser.getCurrentDirectory().equals(currentDirectory)?currentDirectory:
                    jFileChooser.getCurrentDirectory()==null?currentDirectory:jFileChooser.getCurrentDirectory();
            return null;
        }

        public List<File> openMultiFileDialog() {
            JFileChooser jFileChooser = new JFileChooser(currentDirectory);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showDialog(null, "Choose");
            if(jFileChooser.getSelectedFiles()!=null) {
                File[] f = jFileChooser.getSelectedFiles();
                if(getDim()==1) {
                    for(File f1 : f)
                    {
                        setElem(f1, getData1d().size());

                    }
                }
            }
            currentDirectory = jFileChooser.getCurrentDirectory().equals(currentDirectory)?currentDirectory:
                    jFileChooser.getCurrentDirectory()==null?currentDirectory:jFileChooser.getCurrentDirectory();
            return getData1d();
        }
        public void setToDialog(Object fileS, Representable r, String property, int dim, int row, int col)
        {
        }
        public void setToRepresentable(Object fileS, Representable r, ObjectDetailDescription objectDetailDescription)
        {

        }

        public void saveFiles(Scene scene, DataModel dataModel)
        {

        }
    }
