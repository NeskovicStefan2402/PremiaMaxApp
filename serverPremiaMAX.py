from flask import Flask,render_template,request,jsonify
import sqlite3,datetime

app=Flask(__name__)

@app.route('/post', methods=['POST'])
def create_sastojak():
        data = request.get_json()
        unesiteUBazu(data['DIN']['DIN'],data['DIN']['DOLAR'],data['DIN']['EVRO'],data['DIN']['FUNTA'],data['DIN']['KM'],
                     data['DOLAR']['DIN'],data['DOLAR']['DOLAR'],data['DOLAR']['EVRO'],data['DOLAR']['FUNTA'],data['DOLAR']['KM'],
                     data['EVRO']['DIN'],data['EVRO']['DOLAR'],data['EVRO']['EVRO'],data['EVRO']['FUNTA'],data['EVRO']['KM'],
                     data['FUNTA']['DIN'],data['FUNTA']['DOLAR'],data['FUNTA']['EVRO'],data['FUNTA']['FUNTA'],data['FUNTA']['KM'],
                     data['KM']['DIN'],data['KM']['DOLAR'],data['KM']['EVRO'],data['KM']['FUNTA'],data['KM']['KM'])
        return jsonify({'poruka': 'Kurs je uspesno kreiran!'})

@app.route('/post/message',methods=['POST'])
def poruka():
    data=request.get_json()
    unesiUBazuPoruku(data['email'],data['message'])
    return jsonify({'poruka': 'Poruka je uspesno poslata!'})

@app.route('/poruke',methods=['GET'])
def strana2():
    data=ispisiIzBazePoruke()
    print(data)
    nmp=[]
    for i in data:
        nmp1={}
        nmp1['id']=i[0]
        nmp1['datum'] =i[1]
        nmp1['email'] =i[2]
        nmp1['poruka'] =i[3]
        nmp.append(nmp1)

    return jsonify(nmp)

@app.route('/promeneKursa',methods=['GET'])
def strana3():
    data=ispisiBazu()
    print(data)
    nmp=[]
    for i in data:
        nmp1={}
        nmp1['id']=i[0]
        nmp1['datum'] =i[1]

        nmp2={}
        nmp2['DIN']=i[2]
        nmp2['DOLAR']=i[3]
        nmp2['EVRO']=i[4]
        nmp2['FUNTA']=i[5]
        nmp2['KM']=i[6]
        nmp1['DIN']=nmp2

        nmp3 = {}
        nmp3['DIN'] = i[7]
        nmp3['DOLAR'] = i[8]
        nmp3['EVRO'] = i[9]
        nmp3['FUNTA'] = i[10]
        nmp3['KM'] = i[11]
        nmp1['DOLAR'] = nmp3

        nmp4 = {}
        nmp4['DIN'] = i[12]
        nmp4['DOLAR'] = i[13]
        nmp4['EVRO'] = i[14]
        nmp4['FUNTA'] = i[15]
        nmp4['KM'] = i[16]
        nmp1['EVRO'] = nmp4

        nmp5 = {}
        nmp5['DIN'] = i[17]
        nmp5['DOLAR'] = i[18]
        nmp5['EVRO'] = i[19]
        nmp5['FUNTA'] = i[20]
        nmp5['KM'] = i[21]
        nmp1['FUNTA'] = nmp5

        nmp6 = {}
        nmp6['DIN'] = i[22]
        nmp6['DOLAR'] = i[23]
        nmp6['EVRO'] = i[24]
        nmp6['FUNTA'] = i[25]
        nmp6['KM'] = i[26]
        nmp1['KM'] = nmp2

        nmp.append(nmp1)

    return jsonify(nmp)


def kreiranjeBaze():
    conn = sqlite3.connect('mydb.db')
    cur=conn.cursor()
    cur.execute('''CREATE TABLE kurs
                (id numeric, datum date, dindin real, dindolar real,dinevro real,dinfunta real,dinkm real,
                                            dolardin real, dolardolar real,dolarevro real,dolarfunta real,dolarkm real,
                                            evrodin real, evrodolar real,evroevro real,evrofunta real,evrokm real,
                                            funtadin real, funtadolar real,funtaevro real,funtafunta real,funtakm real,
                                            kmdin real, kmdolar real,kmevro real,kmfunta real,kmkm real)''')
    conn.commit()
    conn.close()

@app.route('/kurs',methods=['GET'])
def strana1():
    if(prebrojRedove()>0):
        data=vratiJSON()
        nmp={}
        nmp1={}
        nmp1['DIN']=data[0]
        nmp1['DOLAR'] = data[1]
        nmp1['EVRO'] = data[2]
        nmp1['FUNTA'] = data[3]
        nmp1['KM'] = data[4]
        nmp['DIN']=nmp1

        nmp1 = {}
        nmp1['DIN'] = data[5]
        nmp1['DOLAR'] = data[6]
        nmp1['EVRO'] = data[7]
        nmp1['FUNTA'] = data[8]
        nmp1['KM'] = data[9]
        nmp['DOLAR'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = data[10]
        nmp1['DOLAR'] = data[11]
        nmp1['EVRO'] = data[12]
        nmp1['FUNTA'] = data[13]
        nmp1['KM'] = data[14]
        nmp['EVRO'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = data[15]
        nmp1['DOLAR'] = data[16]
        nmp1['EVRO'] = data[17]
        nmp1['FUNTA'] = data[18]
        nmp1['KM'] = data[19]
        nmp['FUNTA'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = data[20]
        nmp1['DOLAR'] = data[21]
        nmp1['EVRO'] = data[22]
        nmp1['FUNTA'] = data[23]
        nmp1['KM'] = data[24]
        nmp['KM'] = nmp1
    else:
        nmp = {}
        nmp1 = {}
        nmp1['DIN'] = 0
        nmp1['DOLAR'] = 1
        nmp1['EVRO'] =2
        nmp1['FUNTA'] =3
        nmp1['KM'] = 4
        nmp['DIN'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = 5
        nmp1['DOLAR'] = 6
        nmp1['EVRO'] = 7
        nmp1['FUNTA'] = 8
        nmp1['KM'] = 9
        nmp['DOLAR'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = 10
        nmp1['DOLAR'] = 11
        nmp1['EVRO'] = 12
        nmp1['FUNTA'] = 13
        nmp1['KM'] = 14
        nmp['EVRO'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = 15
        nmp1['DOLAR'] = 16
        nmp1['EVRO'] = 17
        nmp1['FUNTA'] = 18
        nmp1['KM'] = 19
        nmp['FUNTA'] = nmp1

        nmp1 = {}
        nmp1['DIN'] = 20
        nmp1['DOLAR'] = 21
        nmp1['EVRO'] = 22
        nmp1['FUNTA'] = 23
        nmp1['KM'] = 24
        nmp['KM'] = nmp1

    return jsonify(nmp)


def unesiteUBazu(dindin, dindolar,dinevro,dinfunta,dinkm,
                                            dolardin , dolardolar ,dolarevro,dolarfunta ,dolarkm ,
                                            evrodin , evrodolar ,evroevro ,evrofunta ,evrokm ,
                                            funtadin , funtadolar ,funtaevro ,funtafunta ,funtakm ,
                                            kmdin , kmdolar ,kmevro ,kmfunta ,kmkm ):
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    datum=datetime.date.today()
    brojac=prebrojRedove()+1
    cur.execute('''INSERT INTO kurs VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)''',(brojac,datum,dindin, dindolar,dinevro,dinfunta,dinkm,dolardin , dolardolar ,dolarevro,dolarfunta ,dolarkm ,evrodin , evrodolar ,evroevro ,evrofunta ,evrokm ,funtadin , funtadolar ,funtaevro ,funtafunta ,funtakm ,kmdin , kmdolar ,kmevro ,kmfunta ,kmkm))
    conn.commit()
    conn.close()

def ispisiBazu():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    cur.execute('''SELECT * FROM kurs''')
    data=cur.fetchall()
    conn.commit()
    conn.close()
    return data



def vratiJSON():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    brojac=prebrojRedove()
    cur.execute('''SELECT * FROM kurs WHERE kurs.id='''+str(brojac))
    data = cur.fetchall()
    conn.commit()
    conn.close()
    return data[0][2:]



def prebrojRedove():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    cur.execute('''SELECT COUNT(*) FROM kurs''')
    data = cur.fetchone()
    conn.commit()
    conn.close()
    return data[0]

def isprazniBazu():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    cur.execute('''DROP TABLE kurs''')
    conn.commit()
    conn.close()
###########################################################################################
####Poruka####


def kreirajBazuPoruka():
    conn=sqlite3.connect('mydb.db')
    cur=conn.cursor()
    cur.execute('CREATE TABLE poruke (id numeric, datum date, email text,poruka text,odgovoreno text )')
    conn.commit()
    conn.close()

def unesiUBazuPoruku(email,poruka):
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    datum=datetime.date.today()
    brojac=prebrojRedovePoruka()+1
    cur.execute('INSERT INTO poruke VALUES(?,?,?,?,?)',(brojac,datum,email,poruka,None))
    conn.commit()
    conn.close()

def prebrojRedovePoruka():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    cur.execute('SELECT COUNT(*) FROM poruke')
    br=cur.fetchone()
    if(br==None):
        brojac=0
    else:
        brojac=br[0]
    conn.commit()
    conn.close()
    return brojac

#def izbrisiBazuPoruke():
#    conn = sqlite3.connect('mydb.db')
#    cur = conn.cursor()
#    cur.execute('DROP TABLE poruke')
#    conn.commit()
#    conn.close()

def ispisiIzBazePoruke():
    conn = sqlite3.connect('mydb.db')
    cur = conn.cursor()
    cur.execute('SELECT * FROM poruke')
    baza = cur.fetchall()
    conn.commit()
    conn.close()
    return baza

if __name__=='__main__':
    app.run(debug=True,host='0.0.0.0')
