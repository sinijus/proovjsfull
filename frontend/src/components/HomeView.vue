<template>
  <div>
    <table class="table" aria-label="contacts-table">
      <thead>
      <tr>
        <th colspan="4" v-if="viewContacts" id="contacts-header">
          <header>
            Kontaktide loetelu
            <button @click="changeView"> Lisa uus kontakt</button>
          </header>
          <input v-model="keyword" type="text" @input="validateAndGetContactsByKeyword" placeholder="otsi sõna järgi">
        </th>
        <th colspan="4" v-if="!viewContacts" id="contacts-input">
          <form @submit.prevent="verifyAndSaveContact">
            <input type="text" v-model="contact.firstName" @input="setContactFirstName" placeholder="Eesnimi">
            <input type="text" v-model="contact.lastName" @input="setContactLastName" placeholder="Perekonnanimi">
            <input type="text" v-model="contact.codename" @input="setContactCodename" placeholder="Koodnimi">
            <input type="text" v-model="contact.phone" @input="setContactPhone" placeholder="Telefoni number">
            <button type="submit"> Salvesta uus kontakt</button>
            <button @click="changeView"> Tagasi</button>
          </form>
        </th>
      </tr>
      <tr v-if="viewContacts">
        <th id="first-name">
          Eesnimi
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.firstName, sort.order.asc)" v-if="viewContacts">▼</button>
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.firstName, sort.order.desc)" v-if="viewContacts">▲</button>
        </th>
        <th id="last-name">
          Perekonnanimi
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.lastName, sort.order.asc)" v-if="viewContacts">▼</button>
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.lastName, sort.order.desc)" v-if="viewContacts">▲</button>
        </th>
        <th id="codename">
          Koodnimi
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.codename, sort.order.asc)" v-if="viewContacts">▼</button>
          <button class="right" @click="setRequestAndGetContacts(sort.parameter.codename, sort.order.desc)" v-if="viewContacts">▲</button>
        </th>
        <th id="phone-number">
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
      }).catch(() => {
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
