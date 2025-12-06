import React, {useEffect, useState} from 'react';

function App() {
  const [students, setStudents] = useState([]);
  const [name,setName] = useState('');
  const [course,setCourse] = useState('');

  useEffect(()=>{ fetch('/api/students').then(r=>r.json()).then(setStudents); },[]);

  const add = async () => {
    const res = await fetch('/api/students', {
      method:'POST', headers:{'Content-Type':'application/json'},
      body: JSON.stringify({name, course})
    });
    const created = await res.json();
    setStudents(prev=>[...prev, created]);
    setName(''); setCourse('');
  };

  return (
    <div style={{padding:20,fontFamily:'Arial'}}>
      <h2>Students (Replica)</h2>
      <div>
        <input placeholder="name" value={name} onChange={e=>setName(e.target.value)} />
        <input placeholder="course" value={course} onChange={e=>setCourse(e.target.value)} />
        <button onClick={add}>Add</button>
      </div>
      <ul>
        {students.map(s=> <li key={s.id}>{s.name} — {s.course}</li>)}
      </ul>
    </div>
  );
}

export default App;
