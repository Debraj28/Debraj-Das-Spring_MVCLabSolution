package com.gl.student.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.student.entities.Student;


@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		tx.commit();
		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.delete(student);
		tx.commit();
		
	}

	@Override
	@Transactional
	public List<Student> searchBy(String name, String department, String country) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		
		String query="";
		if(name.length()!=0 && department.length()!=0 && country.length()!=0) {
			query = "from Student where name like '%"+name+"%' or department like '%"+department+"%' or country like '%"+country+"%'";
		}
		else if(name.length()!=0) {
			query = "from Student where name like '%"+name+"%'";
		}
		else if(department.length()!=0) {
			query = "from Student where department like '%"+department+"%'";
		}
		else if(country.length()!=0) {
			query = "from Student where country like '%"+country+"%'";
		}
		
		List<Student> students = session.createQuery(query).list();
		tx.commit();
		return students;
		
	}

}
