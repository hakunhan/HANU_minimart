package sqa.hanu_minimart.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.repository.RoleRepository;
import sqa.hanu_minimart.repository.UserRepository;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    public void testGetAllAccount() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        this.accountService.getAllAccount(123L, "janedoe", "4105551212", "Name", "42 Main St", 3, 3,
                new String[]{"foo", "foo", "foo"});
    }

    @Test
    public void testGetAllAccount10() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{"Sort"}));
    }

    public void testGetAllAccount2() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        this.accountService.getAllAccount(123L, "janedoe", "4105551212", "Name", "42 Main St", 3, 3,
                new String[]{",", "foo", "foo"});
    }

    @Test
    public void testGetAllAccount3() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{","}));
    }

    @Test
    public void testGetAllAccount4() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{}));
    }

    @Test
    public void testGetAllAccount5() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{"Sort"}));
    }


    @Test
    public void testGetAllAccount8() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{","}));
    }

    @Test
    public void testGetAllAccount9() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.accountService.getAllAccount(123L, "janedoe",
                "4105551212", "Name", "42 Main St", 3, 3, new String[]{}));
    }

    @Test
    public void testGetAccountByCondition() {
        PageImpl<User> pageImpl = new PageImpl<User>(new ArrayList<User>());
        when(this.userRepository.findByNameContainingOrAddressContaining(anyString(), anyString(), (Pageable) any()))
                .thenReturn(pageImpl);
        Page<User> actualAccountByCondition = this.accountService.getAccountByCondition("Name", "42 Main St", null);
        assertSame(pageImpl, actualAccountByCondition);
        assertTrue(actualAccountByCondition.toList().isEmpty());
        verify(this.userRepository).findByNameContainingOrAddressContaining(anyString(), anyString(), (Pageable) any());
    }

    @Test
    public void testGetAccountByCondition2() {
        PageImpl<User> pageImpl = new PageImpl<User>(new ArrayList<User>());
        when(this.userRepository.findByNameContainingOrAddressContaining(anyString(), anyString(), (Pageable) any()))
                .thenReturn(pageImpl);
        Page<User> actualAccountByCondition = this.accountService.getAccountByCondition("Name", "42 Main St", null);
        assertSame(pageImpl, actualAccountByCondition);
        assertTrue(actualAccountByCondition.toList().isEmpty());
        verify(this.userRepository).findByNameContainingOrAddressContaining(anyString(), anyString(), (Pageable) any());
    }
}

