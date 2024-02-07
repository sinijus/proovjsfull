<template>
  <div>
    <table class="table" aria-label="contacts-table">
      <thead>
      <div v-if="viewContacts">
        <header>
          Kontaktide loetelu
          <button @click="changeView"> Lisa uus kontakt</button>
        </header>
        <tr>
          <input v-model="keyword" type="text" @input="validateAndGetContactsByKeyword" placeholder="otsi sõna järgi">
        </tr>
      </div>
        <tr>
          <th>
            Eesnimi
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.firstName,sort.order.asc)" v-if="viewContacts">▼</button>
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.firstName,sort.order.desc)" v-if="viewContacts">▲</button>
          </th>
          <th>
            Perekonnanimi
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.lastName,sort.order.asc)" v-if="viewContacts">▼</button>
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.lastName,sort.order.desc)" v-if="viewContacts">▲</button>
          </th>
          <th>
            Koodnimi
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.codename,sort.order.asc)" v-if="viewContacts">▼</button>
            <button class="right" @click="setRequestAndGetContacts(sort.parameter.codename,sort.order.desc)" v-if="viewContacts">▲</button>
          </th>
          <th>
            Telefoni number
          </th>
        </tr>
      </thead>
      <tbody v-if="viewContacts">
        <tr v-for="contact in contacts" :key="contact.firstName">
          <td>{{ contact.firstName }}</td>
          <td>{{ contact.lastName }}</td>
          <td>{{ contact.codename }}</td>
          <td>{{ contact.phone }}</td>
        </tr>
      </tbody>
      <tbody v-if="!viewContacts">
        <tr>
          <td>
            <input type="text" v-model="contact.firstName" @input="setContactFirstName">
          </td>
          <td>
            <input type="text" v-model="contact.lastName" @input="setContactLastName">
          </td>
          <td>
            <input type="text" v-model="contact.codename" @input="setContactCodename">
          </td>
          <td>
            <input type="text" v-model="contact.phone" @input="setContactPhone">
          </td>
        </tr>
        <button @click="changeView"> Tagasi</button>
        <button @click="verifyAndSaveContact"> Salvesta uus kontakt</button>
      </tbody>
    </table>
  </div>
</template>

<script>
import {sort} from "@/assets/sort.js"

export default {
  name: 'ContactsTable',
  data() {
    return {
      sort: sort,
      viewContacts: true,
      keyword: '',
      sortParameter: '',
      sortOrder: '',
      contacts: [
        {
          firstName: '',
          lastName: '',
          codename: '',
          phone: ''
        }
      ],
      contact: {
        firstName: '',
        lastName: '',
        codename: '',
        phone: ''
      },
      contactError: {
        message: '',
        errorCode: 0
      }
    }
  }, methods: {
    getContacts: function () {
      this.$http.get("/api/contacts", {
            params: {
              filterName: this.keyword,
              sortParameter: this.sortParameter,
              sortOrder: this.sortOrder
            }
          }
      ).then(response => {
        this.contacts = response.data
      }).catch(error => {
      })
    },
    postContact() {
      this.$http.post("/api/contact",
          this.contact
      ).then(() => {
        this.resetFields()
        this.resetContactError()
        this.getContacts()
        this.changeView()
      }).catch(error => {
        this.contactError = error.response.data
        if (this.contactError.errorCode === 112 || this.contactError.errorCode === 113) {
          alert(this.contactError.message)
        }
      })
    },
    validateAndGetContactsByKeyword() {
      this.keyword = this.enableSingleWordInput(this.keyword)
      this.getContacts()
    },
    enableSingleWordInput(word) {
      return word.trim()
    },
    setRequestAndGetContacts(sortPar, sortOrd) {
      this.sortParameter = sortPar
      this.sortOrder = sortOrd
      this.getContacts()
    },
    setContactFirstName() {
      this.contact.firstName = this.enableSingleWordInput(this.contact.firstName)
    },
    setContactLastName() {
      this.contact.lastName = this.enableSingleWordInput(this.contact.lastName)
    },
    setContactCodename() {
      this.contact.codename = this.enableSingleWordInput(this.contact.codename)
    },
    setContactPhone() {
      this.contact.phone = this.enableSingleWordInput(this.contact.phone)
    },
    changeView() {
      this.viewContacts = !this.viewContacts
    },
    verifyAndSaveContact() {
      if (this.contact.firstName === '' || this.contact.lastName === ''
          || this.contact.codename === '' || this.contact.phone === '') {
        alert('Palun täida kõik väljad!')
      } else if (this.isNotNumberThatContainsFourToSixteenDigits(this.contact.phone)) {
        alert('Palun lisa telefoninumber 4-13 kohalise numbrina')
      } else if (this.contact.firstName.length > 255 ||
          this.contact.lastName.length > 255 ||
          this.contact.codename.length > 255) {
        alert('Palun lisa nimed mitte rohkem kui 255 tähemärgi pikkusena')
      } else {
        this.postContact();
      }
    },
    isNotNumberThatContainsFourToSixteenDigits(phoneNumber) {
      return !/\d{4,13}/.test(phoneNumber);
    },
    resetFields() {
      this.keyword = ''
      this.sortParameter = ''
      this.sortOrder = ''
      this.contact.firstName = ''
      this.contact.lastName = ''
      this.contact.codename = ''
      this.contact.phone = ''
    },
    resetContactError() {
      this.contactError.message = ''
      this.contactError.errorCode = 0
    }
  },
  beforeMount() {
    this.getContacts()
  }
}
</script>
