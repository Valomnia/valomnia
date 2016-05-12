/**
 * (c) 2003-2016 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.valomnia.automation.functional;

import static org.junit.Assert.*;


import java.lang.reflect.Type;
import java.util.ArrayList;



import org.junit.Test;
import org.mule.modules.valomnia.ValomniaConnector;
import org.mule.modules.valomnia.entities.TaxList;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class SaveTaxListTestCases extends AbstractTestCase<ValomniaConnector> {

    public SaveTaxListTestCases() {
        super(ValomniaConnector.class);
    }

    @Test
    public void verify() {
        java.lang.String expected1 = "Success Updated";
        java.lang.String expected2 = "Success created";
      TaxList obj = new TaxList();

        boolean exist = false;

        ArrayList<TaxList> list = null;
        
        try {
            list = getConnector().findTaxLists();
        } catch (Exception e) {

            e.printStackTrace();
        }
        Gson gson = new Gson();
        String js = gson.toJson(list);
        @SuppressWarnings("serial")
        Type listType = new TypeToken<ArrayList<TaxList>>() {}.getType();
        ArrayList<TaxList> list1 = new Gson().fromJson(js, listType);

        for (TaxList taxList : list1)
        {
            if (taxList.getName().equals("test TaxList"))
            
                    
                exist = true;
        }
        
        obj.setName("test TaxList");
        obj.setTaxType("Pourcentage %");
        obj.setReference("ref test TaxList");
        
       

        

        if (exist)
            assertEquals(getConnector().saveTaxList(obj), expected1);
        else
            assertEquals(getConnector().saveTaxList(obj), expected2);
    }



}