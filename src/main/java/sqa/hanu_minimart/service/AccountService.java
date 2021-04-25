package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Role;
import sqa.hanu_minimart.model.RoleName;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.payload.UserPayload;
import sqa.hanu_minimart.repository.RoleRepository;
import sqa.hanu_minimart.repository.UserRepository;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public AccountService() {
    }

    public Page<User> getAllAccount(Long id, String username, String phoneNumber, String name, String address, int page, int size, String[] sort){

        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Sort.Direction.valueOf(sort[1].toUpperCase()), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(Sort.Direction.valueOf(sort[1].toUpperCase()), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<User> users = null;
        if (id != null){
            users = userRepository.findById(id, pagingSort);
        } else if (username != null){
            users = userRepository.findByUsername(username, pagingSort);
        } else if (phoneNumber != null) {
            users = userRepository.findByPhoneNumber(phoneNumber, pagingSort);
        } else if (address.equals("_") && name.equals("_")){
            users = userRepository.findAll(pagingSort);
        } else {
            System.out.println(name);
            users = userRepository.findByNameContainingOrAddressContaining(name, address, pagingSort);
        }

        if(users != null){
            return users;
        }

        return null;
    }

    public User getById(Long id){
        return userRepository.findById(id).get();
    }

    public Page<User> getAccountByCondition(String name, String address, Pageable pageable){
        return userRepository.findByNameContainingOrAddressContaining(name, address, pageable);
    }

    public User updateUser(Long id, UserPayload request){
        Optional<User> userData = userRepository.findById(id);

        System.out.println("OKK");

        if(!userData.isPresent()){
            return null;
        }

        User user = userData.get();

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());


        System.out.println(request.getRole().toString());
        Optional<Role> role = roleRepository.findByName(RoleName.valueOf(request.getRole()));

        System.out.println(role.get().toString());

        Set<Role> roles = user.getRoles();

        Role lastRole = roles.stream().findFirst().get();

        roles.remove(lastRole);
        roles.add(role.get());

        User _user = userRepository.save(user);

        return _user;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    // deactivate the account
    public void deleteUser (Long id){
        Optional<User> userData = userRepository.findById(id);
        User user = userData.get();
        user.setStatus("DEACTIVATED");
        userRepository.save(user);
    }
}
