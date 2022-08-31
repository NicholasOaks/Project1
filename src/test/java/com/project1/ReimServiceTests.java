package com.project1;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//import com.project1.models.Reimbursement;
import com.project1.dao.ReimbursementDAO;
import com.project1.services.ReimService;

public class ReimServiceTests {

    ReimbursementDAO rdao = Mockito.mock(ReimbursementDAO.class);
    ReimService rs = new ReimService(rdao);
}
