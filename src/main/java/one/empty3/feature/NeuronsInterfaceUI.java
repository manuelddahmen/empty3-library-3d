/*
 *
 *  * Copyright (c) 2024. Manuel Daniel Dahmen
 *  *
 *  *
 *  *    Copyright 2024 Manuel Daniel Dahmen
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package one.empty3.feature;

import one.empty3.feature.selection.HighlightFeatures;
import one.empty3.feature.tryocr.ReadLines;
import one.empty3.feature.tryocr.SelectColor;
import one.empty3.io.ProcessFile;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NeuronsInterfaceUI {
    private JPanel panel1;
    private JTabbedPane tabbedPaneRun;
    private JTree treeCurrentTree;
    private JList listClassesOfProcesses;
    private JList listClassesOfNeuronsLayers;
    private JTree treeData;
    private JEditorPane editorPane1;
    private JSplitPane splitPane1;
    private JTabbedPane tabbedOptions;
    private JButton parcourirButton;

    public NeuronsInterfaceUI() {
        panel1 = new JPanel();
        tabbedPaneRun = new JTabbedPane();
        treeCurrentTree = new JTree();
        listClassesOfNeuronsLayers = new JList();
        listClassesOfProcesses = new JList();
        treeData = new JTree();
        editorPane1 = new JEditorPane();

    }

    public void setData(Main data) {

    }

    public void init() {
        try {
            ClassListModel model;
            listClassesOfProcesses.setModel(model = new ClassListModel());
            model.add(Classification.class.newInstance());
            model.add(CornerDetectProcess.class.newInstance());
            model.add(CurveFitting.class.newInstance());
            model.add(DBScanProcess.class.newInstance());
            model.add(DericheFilterProcess.class.newInstance());
            model.add(DiffEnergy.class.newInstance());
            model.add(Draw.class.newInstance());
            model.add(ExtremaProcess.class.newInstance());
            model.add(GaussFilterProcess.class.newInstance());
            model.add(GradProcess.class.newInstance());
            model.add(HarrisProcess.class.newInstance());
            model.add(Histogram2.class.newInstance());
            model.add(Histogram3.class.newInstance());
            model.add(HoughTransform.class.newInstance());
            model.add(HoughTransformCircle.class.newInstance());
            model.add(IdentNullProcess.class.newInstance());
            model.add(IsleProcess.class.newInstance());
            model.add(KMeans.class.newInstance());
            model.add(Lines.class.newInstance());
            model.add(Lines3.class.newInstance());
            model.add(Lines4.class.newInstance());
            model.add(Lines5.class.newInstance());
            model.add(Lines5colors.class.newInstance());
            model.add(Lines6.class.newInstance());
            model.add(LocalExtremaProcess.class.newInstance());
            model.add(MagnitudeProcess.class.newInstance());
            model.add(ProxyValue.class.newInstance());
            model.add(ProxyValue2.class.newInstance());
            model.add(ReadLines.class.newInstance());
            model.add(RegionLineCorner.class.newInstance());
            model.add(SelectColor.class.newInstance());
            model.add(Transform1.class.newInstance());
            model.add(TrueHarrisProcess.class.newInstance());
            model.add(Voronoi.class.newInstance());
            model.add(HighlightFeatures.class.newInstance());

            listClassesOfProcesses.setModel(model);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void getData(Main data) {

    }

    public boolean isModified(Main data) {
        return false;
    }

    private static class ClassListModel implements ListModel<ProcessFile> {
        private List<ProcessFile> data = new ArrayList<>();

        @Override
        public int getSize() {
            return data.size();
        }

        @Override
        public ProcessFile getElementAt(int index) {
            return data.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {

        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }

        public List<ProcessFile> getData() {
            return data;
        }

        public void setData(List<ProcessFile> data) {
            this.data = data;
        }

        public void add(ProcessFile d) {
            data.add(d);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        NeuronsInterfaceUI neuronsInterfaceUI = new NeuronsInterfaceUI();
        neuronsInterfaceUI.panel1.setLayout(new GridBagLayout());
        neuronsInterfaceUI.splitPane1 = new JSplitPane();
        neuronsInterfaceUI.panel1.add(neuronsInterfaceUI.splitPane1);
        neuronsInterfaceUI.splitPane1.setLeftComponent(neuronsInterfaceUI.treeCurrentTree);
        neuronsInterfaceUI.tabbedOptions = new JTabbedPane();
        neuronsInterfaceUI.splitPane1.setRightComponent(neuronsInterfaceUI.tabbedOptions);
        neuronsInterfaceUI.tabbedOptions.addTab("Classes of processes", neuronsInterfaceUI.listClassesOfProcesses);
        neuronsInterfaceUI.tabbedOptions.addTab("Classes of neurons' layers", neuronsInterfaceUI.listClassesOfNeuronsLayers);
        JFrame frame = new JFrame("NeuronsInterfaceUI");
        frame.setContentPane(neuronsInterfaceUI.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
