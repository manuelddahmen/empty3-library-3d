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

package one.empty3.gui;

/**
 * Created by manue on 15-07-19.
 */
public class ObjectDetailDescription {
    private String name;
    private String descrition;
    private int dim;
    private String indexes;
    private Class clazz;
    private Object value;
    String[] columnNames = {"Formal Name", "Description", "Dim", "Indices", "ObjectType", "Data"};
    Class<?>[] columnClass = {String.class, String.class, String.class, Class.class, Object.class};

    public ObjectDetailDescription(String name, String descrition, int i, String i1, Class aClass, Object s) {
        this.name = name;
        this.descrition = descrition;
        this.dim = i;
        this.indexes = i1;
        this.clazz = aClass;
        this.value = s;
    }

    public Object get(int columnIndex) {
        Object o = null;
        switch (columnIndex)
        {
            case 0:
                o = name;
                break;
            case 1:
                o =descrition;
                break;
            case 2:
                o =dim;
                break;
            case 3:
                o =indexes;
                break;
            case 4:
                o =clazz;
                break;
            case 5:
                o = value;
                break;

        }
        return o;
    }

    public void set(int columnIndex, Object aValue) {
        switch (columnIndex)
        {
            case 0:
                name = (String)aValue;
                break;
            case 1:
                descrition= (String)aValue;
                break;
            case 2:
                dim = (int)Integer.parseInt(aValue.toString());
                break;
            case 3:
                indexes = ""+aValue;
                break;
            case 4:
                clazz = (Class)aValue;
                break;
            case 5:
                value = aValue;
                break;

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public String getIndexes() {
        return indexes;
    }

    public void setIndexes(String indexes) {
        this.indexes = indexes;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ObjectDetailDescription{\n" +
                "name='" + name + "\'\n" +
                ", descrition='" + descrition + "\'\n" +
                ", dim=" + dim +
                ", indexes='" + indexes + "\'\n" +
                ", clazz=" + clazz +
                ", value=" + value +
                '}';
    }
}
