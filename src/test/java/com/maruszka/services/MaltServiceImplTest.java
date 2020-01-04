package com.maruszka.services;

import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.springdatajpa.MaltServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;


public class MaltServiceImplTest {

    private MaltServiceImpl maltServiceImpl;

    @Mock
    MaltRepository maltRepository;

    @Mock
    BatchRepository batchRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        maltServiceImpl = new MaltServiceImpl(maltRepository, batchRepository);
    }

//    @Test(expected = NotFoundException.class)
//    public void getMaltByIdTestNotFound() throws Exception {
//        Optional<Malt> maltOptional = Optional.empty();
//
//        when(maltRepository.findById(anyLong())).thenReturn(maltOptional);
//
//        Malt maltReturned = maltSDJpaService.findById(1L);
//    }

}
