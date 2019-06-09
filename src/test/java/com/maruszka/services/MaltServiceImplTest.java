package com.maruszka.services;

import com.maruszka.exceptions.NotFoundException;
import com.maruszka.model.Malt;
import com.maruszka.repositories.BatchRepository;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.springdatajpa.MaltSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;


public class MaltServiceImplTest {

    private MaltSDJpaService maltSDJpaService;

    @Mock
    MaltRepository maltRepository;

    @Mock
    BatchRepository batchRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        maltSDJpaService = new MaltSDJpaService(maltRepository, batchRepository);
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
