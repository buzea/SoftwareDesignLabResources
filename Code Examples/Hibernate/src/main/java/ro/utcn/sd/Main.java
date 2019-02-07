/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcn.sd;

import ro.utcn.sd.business.ShowCartDetails;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.output.CartDetailsDTO;

public class Main {

    /**
     * This is just a small demo.
     * <p>
     * Please also see  ShowCartDetailsTest. (Note ShowCartDetailsTest is not seen in the src folder)
     */
    public static void main(String[] args) {
        long cartId = 1;
        DaoFactory daoFactory = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE);
        ShowCartDetails transactionScript = new ShowCartDetails(daoFactory, cartId);
        CartDetailsDTO execute = transactionScript.execute();
        if (execute != null) {
            System.out.println("Name: " + execute.getName());
            System.out.println("Total:" + execute.getTotal());
            System.out.println("Items:");
            execute.getItems().forEach(System.out::println);
        }
    }
}
