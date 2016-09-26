package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;

public class StorageStub extends Storage{
	
	public StorageStub(){
		
	}

	@Override
	public void save(AddressBook addressbook) throws StorageOperationException{
		//Empty
	}

	@Override
	public AddressBook load() throws StorageOperationException {
		// TODO Auto-generated method stub
		return new AddressBook();
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
