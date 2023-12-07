package com.example.spring_contract.service;

import com.example.spring_contract.model.Client;
import com.example.spring_contract.repository.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    @NonNull
    private ClientRepository repository;

    public void saveClient(String name, String org, String address) {
          int  id = repository.findMaxId() + 1;

        Client client = new Client(id, name, address, org);
        repository.save(client);
    }

    public List<Client> find(String id, String org, String address) {
        if (id != null && !id.equals("")) {
            try {
                return repository.findClientsById_client(Integer.parseInt(id));
            } catch (Exception e) {
                return repository.findClientsByName(id);
            }
        } else if (org != null && !org.equals("")) {
            return repository.findClientsByOrganization(org);
        } else if (address != null && !address.equals("")) {
            return repository.findByAddres(address);
        } else {
            return repository.findAll();
        }
    }
}
